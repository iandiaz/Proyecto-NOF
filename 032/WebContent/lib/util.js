function onlyNum(event) {
            var key = window.event ? event.keyCode : event.which;
			//alert(event.keyCode);
            if (event.keyCode == 8 || event.keyCode == 46
             || event.keyCode == 37 || event.keyCode == 39 || event.keyCode == 9 || event.keyCode == 190 || (key >= 96 && key <= 105)) {
                return true;
            }
            else if ( key < 48 || key > 57 ) {
                return false;
            }
            else return true;
        };