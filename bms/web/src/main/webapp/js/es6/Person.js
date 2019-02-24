/**
 * Created by Green on 2018/8/9.
 */

function extend(to, _from) {
    for (const key in _from) {
        to[key] = _from[key];
    }
    return to
}

let mid = 0;

class Model {
    constructor(props) {
        this.init(props);
    }
    init(props) {
        this.props = extend({
            name: 'Anonymous',
            gender: 'Unknown'
        }, props || {});
        const self = this;
        Object.keys(this.props).forEach(function(key){
            Object.defineProperty(self, key, {
                get: function(){
                    return self.props[key];
                },
                set: function(value) {
                    self.props[key] = value;
                }
            });
        });
        this.mid = ++mid;
    }
    report(){
        console.log('My name is ' + this.name + ', gender is ' + this.gender);
    }
}

Model.extend = function () {
    const Super = this;
    const Sub = function VModel(options) {
        this.init(options);
    };
    Sub.prototype = Object.create(Super.prototype);
    extend(Sub.prototype, Super.prototype);
    Sub.prototype.constructor = Sub;
    Sub['super'] = Super;

    Sub.extend = Super.extend;
    return Sub
};

export {Model}