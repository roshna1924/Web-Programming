function upDate(txtimg) {
    /* In this function you should
       1) change the url for the background image of the div with the id = "image"
       to the source file of the preview image
       2) Change the text  of the div with the id = "image"
       to the alt text of the preview image
       */
    var src = txtimg.getAttribute( "src" );
    var alt = txtimg.getAttribute( "alt" );
    document.getElementById('image').style.backgroundImage = "url('" + src + "')";
    // alert("Link: "+src);
    // const link = document.getElementById('test').src;
    // document.getElementById('image').style.backgroundImage = "url('" + link + "')";
    document.getElementById('image').innerHTML = alt;

}

function unDo() {
    /* In this function you should
   1) Update the url for the background image of the div with the id = "image"
   back to the orginal-image.  You can use the css code to see what that original URL was
   2) Change the text  of the div with the id = "image"
   back to the original text.  You can use the html code to see what that original text was
   */
    X = document.getElementById('image');
    X.style.backgroundImage = "url('')";
    document.getElementById('image').innerHTML = "Hover over an images to display";

}