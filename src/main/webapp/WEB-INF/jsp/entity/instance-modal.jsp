<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="header.createinstance"/></h4>
            </div>
            <div class="modal-body form-horizontal">
                <c:choose>
                    <c:when test="${empty entityInstance}">
                        <spring:url var="action"
                                    value='/home/entity/${EntitySchema.id}/instance/add'/>
                    </c:when>
                    <c:otherwise>
                        <spring:url var="action"
                                    value='/home/entity/${EntitySchema.id}/instance/update/${entityInstance.id}'/>
                    </c:otherwise>
                </c:choose>
                <form id="Instance" name="Instance" action="${action}" method="post">
                    <c:forEach var="field" items="${EntitySchema.fields}">
                        <c:set var="coincidence" value="false" scope="page"/>
                        <c:if test="${not empty entityInstance}">
                            <c:forEach var="value" items="${entityInstance.values}">
                                <c:choose>
                                    <c:when test="${field.fieldId eq value.field.fieldId}">
                                        <c:set var="coincidence" value="true" scope="page"/>
                                        <c:set var="coincidedValue" value="${value}" scope="page"/>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </c:if>
                        <c:choose>
                            <c:when test="${field.valueType eq 'STRING'}">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label"><c:out
                                            value="${field.name}"/></label>

                                    <div class="col-sm-8">
                                        <input class="form-control" type="text"
                                               name="<c:out value="${field.fieldId}"/>"
                                               maxlength="<c:out value="${field.size}"/>"
                                                <c:if test="${field.require}">
                                                    required="<c:out
                                                        value="${field.require}"/>"
                                                </c:if>
                                                <c:if test="${coincidence eq true}">
                                                    value="<c:out value="${coincidedValue.value}"/>"
                                                </c:if>/>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${field.valueType eq 'NUMBER'}">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label"><c:out
                                            value="${field.name}"/></label>

                                    <div class="col-sm-8">
                                        <input class="form-control" type="number"
                                               name="<c:out value="${field.fieldId}"/>"
                                               min="<c:out value="${field.minValue}"/>"
                                               max="<c:out value="${field.maxValue}"/>"
                                                <c:set var="step" value="${1}"/>
                                                <c:forEach begin="1"
                                                           end="${field.numberDecimal}">
                                                    <c:set var="step" value="${step / 10}"/>
                                                </c:forEach>
                                               step="<c:out value="${step}"/>"
                                                <c:if test="${field.require}">
                                                    required="<c:out
                                                        value="${field.require}"/>"
                                                </c:if>
                                                <c:if test="${coincidence eq true}">
                                                    value="<c:out
                                                        value="${coincidedValue.numberValue}"/>"
                                                </c:if>/>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${field.valueType eq 'MULTI_CHOICE'}">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label"><c:out
                                            value="${field.name}"/></label>

                                    <c:choose>
                                        <c:when test="${field.choiceType eq 'radio'}">
                                            <c:forEach var="choice" items="${field.choices}">
                                                <c:set var="checked" value="false" scope="page"/>
                                                <c:forEach var="choiced"
                                                           items="${coincidedValue.choices}">
                                                    <c:if test="${choice.name eq choiced.name}">
                                                        <c:set var="checked" value="true"
                                                               scope="page"/>
                                                    </c:if>
                                                </c:forEach>
                                                <div class="col-sm-8 col-sm-offset-4">
                                                    <div class="radio">
                                                        <label>
                                                            <input type="radio"
                                                                   name="<c:out value="${field.fieldId}"/>"
                                                                   value="<c:out value="${choice.id}"/>"
                                                            <c:if test="${checked eq true}">
                                                                   checked
                                                            </c:if>>
                                                            <c:out value="${choice.name}"/>
                                                        </label>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${field.choiceType eq 'checkbox'}">
                                            <c:forEach var="choice" items="${field.choices}">
                                                <c:set var="checked" value="false"
                                                       scope="page"/>
                                                <c:forEach var="choiced"
                                                           items="${coincidedValue.choices}">
                                                    <c:if test="${choice.name eq choiced.name}">
                                                        <c:set var="checked" value="true"
                                                               scope="page"/>
                                                    </c:if>
                                                </c:forEach>
                                                <div class="col-sm-8 col-sm-offset-4">
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox"
                                                                   name="<c:out value="${field.fieldId}"/>"
                                                                   value="<c:out value="${choice.id}"/>"
                                                            <c:if test="${checked eq true}">
                                                                   checked
                                                            </c:if>>
                                                            <c:out value="${choice.name}"/>
                                                        </label>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${field.choiceType eq 'dropdown'}">
                                            <div class="col-sm-8 col-sm-offset-4">
                                                <select name="<c:out value="${field.fieldId}"/>"
                                                        class="form-control">
                                                    <c:forEach var="choice"
                                                               items="${field.choices}">
                                                        <c:set var="checked" value="false"
                                                               scope="page"/>
                                                        <c:forEach var="choiced"
                                                                   items="${coincidedValue.choices}">
                                                            <c:if test="${choice.name eq choiced.name}">
                                                                <c:set var="checked"
                                                                       value="true"
                                                                       scope="page"/>
                                                            </c:if>
                                                        </c:forEach>
                                                        <option value="<c:out value="${choice.id}"/>"
                                                                <c:if test="${checked eq true}">
                                                                    selected
                                                                </c:if>>
                                                            <c:out value="${choice.name}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </c:when>
                            <c:when test="${field.valueType eq 'TEXT_AREA'}">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">
                                        <c:out value="${field.name}"/>
                                    </label>

                                    <div class="col-sm-8">
                                        <textarea class="form-control"
                                                  rows="<c:out value="${field.countLine}" />"
                                                  name="<c:out value="${field.fieldId}"/>"
                                                  style="resize:none;"
                                                  cols="40"
                                                  id="textarea${field.fieldId}"
                                                <c:if test="${field.require}">
                                                    required="<c:out value="${field.require}"/>"
                                                </c:if>><c:if test="${coincidence eq true}"><c:out
                                                value="${coincidedValue.textAreaValue}"/></c:if></textarea>
                                    </div>
                                </div>
                                <script type="text/javascript">
                                    $(function () {
                                        var textAreaId = "#textarea" + '${field.fieldId}';
                                        var textArea = $(textAreaId);
                                        var maxRows = textArea.attr('rows');
                                        var maxChars = textArea.attr('cols');
                                        textArea.keypress(function (e) {
                                            var text = textArea.val();
                                            var lines = text.split('\n');
                                            if (e.keyCode == 13) {
                                                return lines.length < maxRows;
                                            }
                                            else {
                                                var caret = textArea.get(0).selectionStart;
                                                console.log(caret);

                                                var line = 0;
                                                var charCount = 0;
                                                $.each(lines, function (i, e) {
                                                    charCount += e.length;
                                                    if (caret <= charCount) {
                                                        line = i;
                                                        return false;
                                                    }
                                                    charCount += 1;
                                                });

                                                var theLine = lines[line];
                                                return theLine.length < maxChars;
                                            }
                                        });

                                    });
                                </script>
                            </c:when>
                            <c:when test="${field.valueType eq 'DATE'}">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">
                                        <c:out value="${field.name}"/>
                                    </label>

                                    <div class="col-sm-8">
                                        <div class="input-group date" id="date${field.fieldId}">
                                            <input type="text" class="form-control"
                                                   name="<c:out value="${field.fieldId}"/>"
                                            <c:if test="${field.require}">
                                                   required="<c:out
                                                            value="${field.require}"/>"
                                            </c:if>>
                                            <span class="input-group-addon add-on">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <script type="text/javascript">
                                    $(function () {
                                        var idDateField = "#date" + '${field.fieldId}';
                                        if (${field.fullDate}) {
                                            if (${coincidence eq true}) {
                                                var dateValue = new Date();
                                                var newDate = dateValue.format("mm/dd/yyyy hh:mm TT");
                                                $(idDateField).datetimepicker({
                                                    defaultDate: newDate
                                                });
                                            } else {
                                                $(idDateField).datetimepicker();
                                            }
                                        } else {
                                            if (${coincidence eq true}) {
                                                var dateValue = new Date();
                                                var newDate = dateValue.format("mm/dd/yyyy");
                                                $(idDateField).datepicker({
                                                    defaultDate: newDate
                                                });
                                            } else {
                                                $(idDateField).datepicker({});
                                            }
                                        }
                                    });
                                </script>
                            </c:when>
                            <c:when test="${field.valueType eq 'IMAGE'}">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">
                                        <c:out value="${field.name}"/>
                                    </label>

                                    <div class="col-sm-8">
                                        <input type="text" name="<c:out value="${field.fieldId}"/>"
                                               class="form-control"
                                               id="imageUrl${field.fieldId}"
                                        <c:if test="${coincidence eq true}">
                                               value="<c:out value="${coincidedValue.imageUrl}"/>"
                                        </c:if>
                                        <c:if test="${field.require}">
                                               required="<c:out
                                                        value="${field.require}"/>"
                                        </c:if>>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-8 col-sm-offset-4">
                                        <h6 class="col-sm-5"><spring:message
                                                code="label.modal.image"/></h6>

                                        <div class="col-sm-3" id="imageBox${field.fieldId}">
                                            <c:if test="${coincidence eq true}">
                                                <img src="${coincidedValue.imageUrl}"
                                                     style="max-width: 150px; max-height: 150px">
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                                <script type="text/javascript">
                                    $(document).ready(function () {
                                        var imageUrlId = "#imageUrl" + '${field.fieldId}';
                                        var imageBoxId = "#imageBox" + '${field.fieldId}';

                                        $(imageUrlId).bind('input', function () {
                                            var imageUrlValue = $(this)[0].value;

                                            $.ajax({
                                                type: 'POST',
                                                url: '/ajax/getImageByUrl',
                                                data: ({url: imageUrlValue}),
                                                success: function (fieldList) {
                                                    $(imageBoxId).html(fieldList);
                                                }
                                            });
                                        })
                                    });
                                </script>
                            </c:when>
                            <c:when test="${field.valueType eq 'GPS'}">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">
                                        <c:out value="${field.name}"/>
                                    </label>

                                    <div class="col-sm-8">
                                        <input type="text" name="<c:out value="${field.fieldId}"/>"
                                               class="form-control"
                                               id="map${field.fieldId}"
                                        <c:if test="${coincidence eq true}">
                                               value="${coincidedValue.latitudeValue},${coincidedValue.longitudeValue}"
                                        </c:if>
                                        <c:if test="${field.require}">
                                               required="<c:out
                                                        value="${field.require}"/>"
                                        </c:if>>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-8 col-sm-offset-4">
                                        <div id="googleMap${field.fieldId}" style="height: 300px">

                                        </div>
                                    </div>
                                </div>
                                <script type="text/javascript">
                                    $(function () {
                                        var id = "googleMap" + '${field.fieldId}';
                                        var map;
                                        var marker;

                                        function initialize() {
                                            var mapOptions = {
                                                zoom: 12,
                                                center: new google.maps.LatLng(53.930071, 27.501829),
                                                mapTypeId: google.maps.MapTypeId.ROADMAP
                                            };
                                            map = new google.maps.Map(document.getElementById(id),
                                                    mapOptions);

                                            google.maps.event.addListener(map, 'click', function (event) {
                                                placeMarker(event.latLng);
                                            });

                                            if (${coincidence}) {
                                                var latlng = new google.maps.LatLng('${coincidedValue.latitudeValue}',
                                                        '${coincidedValue.longitudeValue}');
                                                marker = new google.maps.Marker({
                                                    position: latlng,
                                                    map: map
                                                });
                                            }
                                        }

                                        function placeMarker(location) {
                                            if (marker == null) {
                                                marker = new google.maps.Marker({
                                                    position: location,
                                                    map: map
                                                });
                                            } else {
                                                marker.setPosition(location);
                                            }
                                            var mapValueId = "#map" + '${field.fieldId}';
                                            $(mapValueId).val(location.lat() + ',' + location.lng());
                                        }

                                        google.maps.event.addDomListener(window, 'load', initialize);

                                        $("#myModal").on("shown.bs.modal", function (e) {
                                            google.maps.event.trigger(map, "resize");
                                        });
                                    });
                                </script>
                            </c:when>
                            <c:when test="${field.valueType eq 'LOOK_UP'}">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">
                                        <c:out value="${field.name}"/>
                                    </label>

                                    <div class="col-sm-8">
                                        <div id="lookUp${field.fieldId}">

                                        </div>
                                    </div>
                                </div>
                                <script type="text/javascript">
                                    $(function () {
                                        var lookUpId = "#lookUp" + '${field.fieldId}';
                                        $.ajax({
                                            type: 'GET',
                                            url: '/ajax/showLookUpField',
                                            data: 'idLookUp=${field.fieldId}&idLookUpValue=${coincidedValue.lookUpValue}',
                                            success: function (lookUpList) {
                                                $(lookUpId).html(lookUpList);
                                            }
                                        })
                                    });
                                </script>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </form>
            </div>

            <div class="modal-footer">
                <c:choose>
                    <c:when test="${modalSaveButton eq 'Create'}">
                        <form id="Cancel"
                              action="/home/entity/<c:out value="${EntitySchema.id}"/>/instance/list"
                              method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default">Cancel</button>
                        <button form="Instance" type="submit" class="btn btn-primary"><c:out
                                value="${modalSaveButton}"/></button>
                    </c:when>
                    <c:otherwise>
                        <form id="Cancel"
                              action="/home/entity/<c:out value="${EntitySchema.id}"/>/instance/list"
                              method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default">Cancel</button>
                        <button form="Instance" type="submit" class="btn btn-primary"><c:out
                                value="${modalSaveButton}"/></button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

