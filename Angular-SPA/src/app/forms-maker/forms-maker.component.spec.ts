import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormsMakerComponent } from './forms-maker.component';

describe('FormsMakerComponent', () => {
  let component: FormsMakerComponent;
  let fixture: ComponentFixture<FormsMakerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormsMakerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormsMakerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
