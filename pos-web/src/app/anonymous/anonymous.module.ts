import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AnonymousComponent } from './anonymous.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AnonymousRoutingModule } from './anonymous-routing.module';


@NgModule({
  declarations: [
    AnonymousComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AnonymousRoutingModule
  ]
})
export class AnonymousModule { }
