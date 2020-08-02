var $messages = $('.messages-content'),
    d, h, m,
    i = 0;

$(window).load(function() {
  $messages.mCustomScrollbar();
  setTimeout(function() {
    fakeMessage("hi");
  }, 10);
});

function updateScrollbar() {
  $messages.mCustomScrollbar("update").mCustomScrollbar('scrollTo', 'bottom', {
    scrollInertia: 10,
    timeout: 0
  });
}

function setDate(){
  d = new Date()
  if (m != d.getMinutes()) {
    m = d.getMinutes();
    $('<div class="timestamp">' + d.getHours() + ':' + m + '</div>').appendTo($('.message:last'));
  }
}

function insertMessage() {
  msg = $('.message-input').val();
  if ($.trim(msg) == '') {
    return false;
  }
  $('<div class="message message-personal">' + msg + '</div>').appendTo($('.mCSB_container')).addClass('new');
  setDate();
  $('.message-input').val(null);
  updateScrollbar();
  setTimeout(function() {
	 
	  var chatMessage = {
	            text: msg
	           
	        }
	 // alert(chatMessage.text);
	  var request = $.ajax({
		  url: 'http://localhost:7000/chat',
		  type: 'POST',
		  data: JSON.stringify(chatMessage),
          contentType: "application/json; charset=utf-8",
          traditional: true,
		  dataType: 'json'
		});

		request.done(function(msg) {
		  //$("#log").html( msg );
			 fakeMessage(msg.text);
		});

		request.fail(function(jqXHR, textStatus) {
		  alert( "Request failed: " + textStatus );
		});
	  
	 
  }, 10 + (Math.random() * 20) * 100);
}

$('.message-submit').click(function() {
  insertMessage();
});

$(window).on('keydown', function(e) {
  if (e.which == 13) {
    insertMessage();
    return false;
  }
})

var Fake = [
  'Hi there, I\'m Fabio and you?',
  'Nice to meet you',
  'How are you?',
  'Not too bad, thanks',
  'What do you do?',
  'That\'s awesome',
  'Codepen is a nice place to stay',
  'I think you\'re a nice person',
  'Why do you think that?',
  'Can you explain?',
  'Anyway I\'ve gotta go now',
  'It was a pleasure chat with you',
  'Time to make a new codepen',
  'Bye',
  ':)'
]

	function fakeMessage(retMsg) {
	  if ($('.message-input').val() != '') {
		return false;
	  }
	  $('<div class="message loading new"><figure class="avatar"><img src="/js/IMAG0164_1_1_1_1.jpg" /></figure><span></span></div>').appendTo($('.mCSB_container'));
	  updateScrollbar();

	  setTimeout(function() {
		$('.message.loading').remove();
		$('<div class="message new"><figure class="avatar"><img src="/js/IMAG0164_1_1_1_1.jpg" /></figure>' + retMsg + '</div>').appendTo($('.mCSB_container')).addClass('new');
		setDate();
		updateScrollbar();
		i++;
	  }, 10 + (Math.random() * 20) * 100);

	}


