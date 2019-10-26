import { IUser } from './IUser';

export class User implements IUser {

    constructor(public id: Number, public name: String) {
    }
}