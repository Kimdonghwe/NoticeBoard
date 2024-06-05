document.addEventListener("DOMContentLoaded", function () {
	var yearSelect = document.getElementById("birthYear");
	var currentYear = new Date().getFullYear();
	var earliestYear = 1900;

	for (let year = currentYear; year >= earliestYear; year--) {
		var option = document.createElement("option");
		option.value = year;
		option.textContent = year + "년";
		yearSelect.appendChild(option);
	}
});
