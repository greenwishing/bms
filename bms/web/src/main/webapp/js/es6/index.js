/**
 * Created by Green on 2018/8/9.
 */

import {Model} from './Person.js'

let person = new Model({name: 'Zhang', gender: 'Male'});
 person.report();
 console.log(person)

let Programmer = Model.extend();
Programmer.prototype.report = function(){
    console.log('My name is ' + this.name + ', gender is ' + this.gender + ', tech: ' + this.tech);
};
var programmer = new Programmer({name: 'Zhang', gender: 'Male', tech: 'Java'});
programmer.report();
console.log(programmer)
