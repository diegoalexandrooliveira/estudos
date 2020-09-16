import { ChangeDetectionStrategy, Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from '../product';



@Component({
  selector: 'pm-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProductListComponent {
  pageTitle = 'Products';

  @Input() errorMessage: string;
  @Input() products: Product[];
  @Input() selectedProduct: Product;
  @Input() displayCode: boolean;

  @Output() productWasSelected = new EventEmitter<Product>();
  @Output() displayCodeChanged = new EventEmitter<void>();
  @Output() addProduct = new EventEmitter<void>();

  public newProduct(): void {
    this.addProduct.emit();
  }

  public checkChanged(): void {
    this.displayCodeChanged.emit();
  }

  public productSelected(product: Product): void {
    this.productWasSelected.emit(product);
  }
}
