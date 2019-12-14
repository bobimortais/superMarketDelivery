import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuperMarketAppComponent } from './super-market-app.component';

describe('SuperMarketAppComponent', () => {
  let component: SuperMarketAppComponent;
  let fixture: ComponentFixture<SuperMarketAppComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuperMarketAppComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuperMarketAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
