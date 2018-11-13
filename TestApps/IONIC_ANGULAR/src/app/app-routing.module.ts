import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', loadChildren: './home/home.module#HomePageModule' },
  { path: 'LoginRegister', loadChildren: './login-register/login-register.module#LoginRegisterPageModule' },
  { path: 'LoadImageFromDisk', loadChildren: './load-image-from-disk/load-image-from-disk.module#LoadImageFromDiskPageModule' },
  { path: 'LoadImageFromNetwork', loadChildren: './load-image-from-network/load-image-from-network.module#LoadImageFromNetworkPageModule' },
  { path: 'CPUIntensive', loadChildren: './cpuintensive/cpuintensive.module#CPUIntensivePageModule' },
  { path: 'MemoryManagement', loadChildren: './memory-management/memory-management.module#MemoryManagementPageModule' },
  { path: 'NetworkRequests', loadChildren: './network-requests/network-requests.module#NetworkRequestsPageModule' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
