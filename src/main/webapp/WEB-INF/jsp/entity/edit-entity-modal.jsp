<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%-- Base modal window for create new entity--%>
<div id="entityModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Create Entity</h4>
            </div>
            <div class="modal-body form-horizontal">
                <spring:url var = "action" value='/entity/saveNewEntitySchema'/>
                <form id="EntitySchema" name="EntitySchema" action="${action}" method="post">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Entity Name*</label>
                        <div class="col-sm-8">
                            <input class="form-control" type="text" name="name" placeholder="Entity Name" required="required"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button form="EntitySchema" type="submit" class="btn btn-primary">Create</button>
            </div>
        </div>
    </div>
</div>

<%-- Modal window for edit exist entity--%>
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Edit Entity</h4>
            </div>
            <div class="modal-body form-horizontal">
                <spring:url var = "actionEdited" value='/entity/saveEditedEntitySchema'/>
                <s:form id="EntitySchemaEdit" name="EntitySchemaEdit" action="${actionEdited}" modelAttribute="EntitySchemaEdit" method="post">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Entity Name*</label>
                        <div class="col-sm-8">
                            <s:input type="hidden" path="entitySchemaId"/>
                            <s:input class="form-control" type="text"  placeholder="Entity Name" path="name" required="required"/>
                        </div>
                    </div>
                </s:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button form="EntitySchemaEdit" type="submit" class="btn btn-primary">Edit</button>
            </div>
        </div>
    </div>
</div>