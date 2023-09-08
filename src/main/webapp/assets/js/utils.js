function capitalizeFirstLetter(foo) {
	if (foo!==null) {
		let pseudoToModify = foo.innerText;
		pseudoToModify = pseudoToModify.charAt(0).toUpperCase() + pseudoToModify.substring(1, pseudoToModify.length);
		foo.innerText = pseudoToModify;
	}	
}