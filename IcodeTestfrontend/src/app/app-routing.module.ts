import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FileudComponent } from './fileud/fileud.component';
const routes: Routes = [
  {path:"fileud",component:FileudComponent},
  {path:"",redirectTo:"fileud", pathMatch:"full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
