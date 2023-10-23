import { TestBed } from '@angular/core/testing';

import { FileudService } from './fileud.service';

describe('FileudService', () => {
  let service: FileudService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FileudService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
