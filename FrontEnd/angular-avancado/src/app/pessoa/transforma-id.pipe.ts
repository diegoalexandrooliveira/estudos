import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'transformaId'
})
export class TransformaIdPipe implements PipeTransform {

  transform(id: string): string {
    console.log("Chamando transforma id pipe");
    return `Pipe ID: ${id}`;
  }

}
