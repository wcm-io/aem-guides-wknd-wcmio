(function ($, ns, channel, window, undefined) {
  "use strict";

  var contentFrame = Granite.author.ContentFrame;

  channel.on('click', '.pageinfo-wcmio-genericEdit', function() {
    var path = contentFrame.getContentPath();
    var url = '/editor.html' + path + '.generic-edit.html';
    window.open(url);
  });

}(jQuery, Granite.author, jQuery(document), this));
