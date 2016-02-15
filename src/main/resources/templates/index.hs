 <head>
 <title>Bug_buster</title>
  <link rel="stylesheet" type="text/css" href="http://yegor256.github.io/tacit/tacit.min.css"/>
  <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
   </head>

     <script>
            $( '#search-button' ).click(function() {
             $( '#search' ).toggle();
            });
     </script>

   <nav>Bug_buster</nav>
  <section>
  <a href="/app">See all apps</a>
  <hr />
      <button id="search-button">Search</button>
  <hr />
    <section>
    <form id="search" style="display:none;" action="/search">
         Search <input name="appname" type="text"/>
        <button type="submit">Go</button>
    </form>
    </section>
    <section>
    <form id='createapp' action="/create-app">
     App name <input name="appname" type="text"/>
     <button type="submit">Create</button>
     </form>
  </section>