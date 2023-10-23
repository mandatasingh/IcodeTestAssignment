import { Component } from '@angular/core';
import { FileudService } from '../fileud.service';
@Component({
  selector: 'app-fileud',
  templateUrl: './fileud.component.html',
  styleUrls: ['./fileud.component.css'],
  template: `
  <button (click)="downloadTemplate()">Download Excel Template</button>
  <input type="file" (change)="onFileChange($event)" />
  <button (click)="uploadTemplate()">Upload Excel File</button>
`
})
export class FileudComponent {
  private selectedFile:File|undefined;
  constructor(private fs:FileudService ){}
  ans:string=""
  downloadTemplate() {
    this.fs.downloadTemplate().subscribe(
      (data: Blob) => {
        const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = 'your_template.xlsx';
        link.click();
      },
      error => {
        console.error('Error downloading template', error);
      }
    );
  }

  onFileChange(event: any) {
    this.selectedFile = event.target.files[0];
  }

  uploadTemplate() {
    if (this.selectedFile) {
      this.fs.uploadTemplate(this.selectedFile).subscribe(
        response => {
          console.log('File uploaded successfully:', response);
        },
        error => {
          console.error('Error uploading file', error);
        }
      );
    } else {
      console.error('No file selected for upload');
    }
  }

  showData()
  {
    this.fs.getAllUserFile().subscribe({
      next:(result:any)=>this.ans=result,
      error:(error:any)=>console.log(error),
      complete:()=>console.log("complete")
    })
  }
}
