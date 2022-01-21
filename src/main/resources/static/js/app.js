
$(document).ready(function () {
  $('#dtBasicExample').DataTable({
    "paging": "simple_numbers" // false to disable pagination (or any other option)
  });
  $('.dataTables_length').addClass('bs-select');
});


//function nextPage(){
//    const params = new URLSearchParams(location.search);
//    console.log(params + "id");
//    currentPageSize = params.get("pSize");
//    currentPageSize++;
//    params.set("pSize",currentPageSize);
//    window.history.replaceState({}, '', `${location.pathname}?${params}`);
//    location.reload();
//}