cmt.bpdesign.Editor = {
    project: null,
    createProject: function(projectName){
        this.model = new cmt.bpdesign.Model(projectName);
    },
    saveProject: function(){
       //todo: make server side to persist bp project
    }

};