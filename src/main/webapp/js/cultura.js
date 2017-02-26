function showPainting() {
	$('#assetTypeDiv').empty();
	$('#assetTypeDiv').append('Peinture');
	$('#modalLabel').empty();
	$('#modalLabel').append('Peinture');
	$('#name').attr('placeholder','');
	$('#name').attr('placeholder','Peinture');
	$('#name').attr('data-validation-required-message','');
	$('#name').attr('data-validation-required-message','Please enter your paints.');
	$('#btnSearch').attr('onclick','');
	$('#btnSearch').attr('onclick','searchAsset(\'GooglePaintingSearch\')');
}

function showPlaces() {
	$('#assetTypeDiv').empty();
	$('#assetTypeDiv').append('Lieux');
	$('#modalLabel').empty();
	$('#modalLabel').append('Places');
	$('#name').attr('placeholder','');
	$('#name').attr('placeholder','Places');
	$('#name').attr('data-validation-required-message','');
	$('#name').attr('data-validation-required-message','Please enter your place.');
	$('#btnSearch').attr('onclick','');
	$('#btnSearch').attr('onclick','searchAsset(\'GooglePlacesSearch\')');
}

function showBook() {
	$('#assetTypeDiv').empty();
	$('#assetTypeDiv').append('Livres');
	$('#modalLabel').empty();
	$('#modalLabel').append('Livres');
	$('#name').attr('placeholder','');
	$('#name').attr('placeholder','Livres');
	$('#name').attr('data-validation-required-message','');
	$('#name').attr('data-validation-required-message','Please enter your book.');
	$('#modalAdd').empty();
	$('#modalAdd').append('<label class="radio-inline"><input type="radio" name="nameType" id="inputType1" value="isbn" required>ISBN</label>');
	$('#modalAdd').append('<label class="radio-inline"><input type="radio" name="nameType" id="inputType2" value="intitle">Titre</label>');
	$('#btnSearch').attr('onclick','');
	$('#btnSearch').attr('onclick','searchAsset(\'GoogleBookSearch\')');
}

function showPopup(title,category) {
	$("#formTitle2").empty();
	$("#formTitle2").append(title);
	switch(category)  {
		case 'paint':
			showPainting();
			break;
		case 'place':
			showPlaces();
			break;
		case 'book':
			showBook();
			break;
		default:
			alert('categorie non connue');
	}
}



function searchAsset(assetType) {
	//get elements value
	var name = $("#name").val();
	if(name == "") {
		$("#modalError").empty();
		$("#modalError").append("Merci de compléter le formulaire");
		return;
	}
	var filtertype = $("input[name='nameType']:checked").val();
	if(filtertype == "" ||  filtertype == undefined) {
		$("#modalError").empty();
		$("#modalError").append("Merci de compléter le formulaire");
		return;	
	}
	$.ajax({
		type: "POST",
		url : "/cultura/searchAsset",
		data: { filterName : name, assettype : assetType, filterType : filtertype},
		success: function(data) {
			
			$("#response-table").empty();
			var obj = jQuery.parseJSON(data);
			contentHTML = "";
			$.each(obj,function(key,value) {
				if (key != "header") {
					//contentHTML += "<tr>";
					contentHTML += "<div class=\"col-sm-6 col-md-4\">" 
					$.each(value,function(keyObj,valueObj) {
						if(keyObj == "cover") {
							contentHTML += "<div class=\"thumbnail\"><img src=\""+valueObj+"\"><div class=\"caption\">";
						}else {
							contentHTML += "<p>"+valueObj+"</p>";
						}
					});
					contentHTML += "</div></div></div>"
				}
				
			});
			//contentHTML += "</tbody>";
			$("#response-table").append(contentHTML);
		},
		error: function(data) {
			//alert("error");
			divError = '<div class="danger">'+data+'</div>';
			$("#response-table").append(divError);
			//$("#portfolioModal1").hide();
			//$(".modal-backdrop").hide();
		}
	});
	
}