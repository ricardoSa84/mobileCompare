package com.example.rsa.androidnativobench.networking;

import android.content.Context;

import com.example.rsa.androidnativobench.general.AppConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ServiceCallManager {

    public static OkHttpClient okHttpClient;

    private static boolean safeClientToUse = true;

    public static void setSafeClientToUse(boolean safeClientToUse) {
        ServiceCallManager.safeClientToUse = safeClientToUse;
    }

    private static Context context;

    public ServiceCallManager(Context c) {
        context = c;
    }

    private static Retrofit retrofit = null;

    public static Retrofit getServiceCallManager(final String url, final Integer timeout, Context c) {
        context = c;
        setSafeClientToUse(safeClientToUse);
        if (retrofit == null /*|| !url.equals(retrofit.baseUrl())*/) {
            if (AppConfig.getEnv() == AppConfig.Enviroment.prod)
                okHttpClient = getPinnedOkHttpClient(timeout);
            else
                okHttpClient = getUnpinnedOkHttpClient();
                retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(okHttpClient)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getPinnedOkHttpClient(final Integer timeout) {
        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add("", "")
                .build();

        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
                        .addHeader("Content-Type", "application/json")
                        //.removeHeader("jwt")
                        //.addHeader("jwt", Commun.getToken(context))
                        .build();
                Response response = chain.proceed(request);
                return response;
            }
        })
                .connectTimeout(timeout == null ? AppConfig.getServiceTimeout() : timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout == null ? AppConfig.getServiceTimeout() : timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout == null ? AppConfig.getServiceTimeout() : timeout, TimeUnit.MILLISECONDS)
                .certificatePinner(certificatePinner)
                .cache(null)
                .build();
    }

    private static OkHttpClient getUnpinnedOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    request = request.newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Accept-Version", "1.0.0")
                            .removeHeader("Authorization")
                            .addHeader("Authorization", AppConfig.getToken())
                            .build();

                    Response response = chain.proceed(request);
                    return response;
                }
            });
            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
