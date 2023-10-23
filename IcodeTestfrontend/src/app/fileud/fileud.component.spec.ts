import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FileudComponent } from './fileud.component';

describe('FileudComponent', () => {
  let component: FileudComponent;
  let fixture: ComponentFixture<FileudComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FileudComponent]
    });
    fixture = TestBed.createComponent(FileudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
