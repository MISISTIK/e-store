function check(val) {
    var elem1 = $('#defaultInline' + val);
    if (elem1.is(':checked')) {
        if (val === 'All') {
            window.location = '/products';
        } else {
            window.location = '/products?cat=' + val;
        }
    }
}
