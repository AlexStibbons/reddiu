import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAddNewsComponent } from './edit-add-news.component';

describe('EditAddNewsComponent', () => {
  let component: EditAddNewsComponent;
  let fixture: ComponentFixture<EditAddNewsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditAddNewsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditAddNewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
