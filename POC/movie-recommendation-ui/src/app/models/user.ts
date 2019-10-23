export class User {

    constructor(private id: Number, private name: String) {

    }

    public getId(): Number {
        return this.id;
    }

    public getName(): String {
        return this.name;
    }
}