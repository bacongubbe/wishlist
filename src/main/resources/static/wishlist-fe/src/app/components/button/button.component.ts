import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-button',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss']
})
export class ButtonComponent {

  editable = false;
  message = 'Nope!'

  slamdunk() {
    this.editable = !this.editable
    if (this.editable) {
      this.message = 'Go ahead!'
    } else {
      this.message = 'Nope!'
    }
  }

}
