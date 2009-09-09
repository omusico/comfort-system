cmt.bpdesign.model.Project = function(_name){
    $.extend(cmt.common.Named(_name), {
        processes: [],
        addProcess: function(processName){
            var p = new cmt.bpdesign.model.Process(processName);
            this.processes[processName] = p;
        }

    })
};