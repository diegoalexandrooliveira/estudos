import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'forca'
})

export class ForcaPipe implements PipeTransform {
  transform(value: any, ...args: any[]): any {
    if (value < 10) {
      return `${value} (fraco)`;
    } else if (value >= 10 && value < 20) {
      return `${value} (forte)`;
    } else {
      return `${value} (inacreditavel)`;
    }
  }
}
