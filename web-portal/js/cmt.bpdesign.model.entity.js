cmt.bpdesign.model.Entity = function(_name){
    var _id = new cmt.common.Id(cmt.bpdesign.model.newId());
    return $.extend(_id, new cmt.common.Named(_name));
}