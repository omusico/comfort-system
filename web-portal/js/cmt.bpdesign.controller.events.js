cmt.bpdesign.controller.Events = {
    listeners: [],
    fireEvent: function(eventName, params) {
        if (eventName != null){
            var e = eventName.toUpperCase();
            if (this.listeners[e]){
                for (var i = 0; this.listeners[e].length - 1; i++ ){
                    var callback = this.listeners[e][i];
                    callback(params);
                }
            }
        }
    },
    addListener: function(eventName, callback) {
        if (eventName != null && callback != null)
        {
            var e = eventName.toUpperCase();
            this.listeners[e] = this.listeners[e] || [];
            var l = this.listeners[e];
            if (l.indexOf(callback) == -1){
                l.push(callback);
            }
        }
    },
    removeListener: function(eventName, callback) {
        if (eventName != null && obj != null)
        {
            var e = eventName.toUpperCase();
            this.listeners[e] = this.listeners[e] || [];
            var l = this.listeners[e];
            var i = l.indexOf(callback);
            if (i != -1){
                l.splice(i, 1);
            }
        }
    }    
}