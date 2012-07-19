function move(target) {
	document.menuform.action = target;
	document.menuform.submit();
}

function moveForForm(target) {
	document.form.action = target;
	document.form.submit();
}

function moveForFormAndAttendance(target, employeeId, day) {
	document.form.action = target;
	document.form.employeeId.value = employeeId;
	document.form.day.value = day;
	document.form.submit();
}

function calcChargeForPaymentSlip(index) {
	var unitPrice = eval(document.form.elements["paymentSlipDetailList[" + index + "].unitPrice"].value);
	var amount = eval(document.form.elements["paymentSlipDetailList[" + index + "].amount"].value);

	if (!isNaN(unitPrice) && !isNaN(amount)) {
		document.form.elements["charge[" + index + "]"].value = unitPrice * amount;		
	} else {
		document.form.elements["charge[" + index + "]"].value = "";				
	}
}
