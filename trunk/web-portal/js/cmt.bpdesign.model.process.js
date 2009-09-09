cmt.bpdesign.model.Process = function(_name){
    $.extend(cmt.common.Named(_name), {
        nodes: [],
        addNode: function(_node){
           this.nodes[_node.name] = _node;
        }
    });
};

