cmt.bpdesign.view.WorkSpace = function(_process){
    var _board = $('#editor').append('<div class="workspace"/>');
    var nodesUI = null;
    function minimizeWS(){
        board.css("height", "0em");
    }

    function createUI(process){
        // paint nodes
        var n = null;
        nodesUI = [];
        for (var i = 0; i < process.nodes.length; i++){
            nodesUI.push(new cmt.bpdesign.view.NodeUI(process.nodes[i], this));
        }
        //todo: ribes view

    }

    function updateWS(process){
       if (process != this.process)
           minimizeWS();
       else
          if (nodesUI == null) createUI(this.process);
    }
    
    cmt.bpdesign.controller.Events.addListener("project.change.process", updateWS);
    return $.extend(this, {
        process: _process,
        board: _board 
    });
};
