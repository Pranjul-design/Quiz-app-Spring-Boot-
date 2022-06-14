//Pagination
function getPageList(totalPages,page,maxlength){
    function range(start,end){
        return Array.from(Array(end-start+1),(_,i)=>i+ start);
    }
    var sideMove = maxlength < 9 ? 1 : 2;
    var leftMove = (maxlength-sideMove*2-3) >>1;
    var rightMove = (maxlength -sideMove*2-3) >>1;

    if(totalPages <= maxlength){
        return range(1,totalPages);
    }
    if(page <= maxlength-sideMove-1-rightMove){
        return range(1,maxlength-sideMove-1).concat(0,range(totalPages-sideMove+1,totalPages));
    }
    if(page >= totalPages-sideMove-1-rightMove){
        return range (1,sideMove).concat(0,range(totalPages-sideMove-1-rightMove-leftMove,totalPages))
    }
    return range(1,sideMove).concat(0,range(page-leftMove,page+rightMove),0,range(totalPages-sideMove+1,totalPages));

}

$(function(){
    var numOfItems =$(".line-content").length;
    var limitPerPage = 10; //items want to visible in single page
    var totalPages = Math.ceil(numOfItems/limitPerPage);
    var paginationSize = 5;
    var currentPage;

    function showPage(whichPage){
        if(whichPage <1 || whichPage > totalPages) return false;
        currentPage = whichPage;
        $(".line-content").hide().slice((currentPage-1)*limitPerPage,currentPage*limitPerPage).show();
        $(".pagination li").slice(1,-1).remove();

        getPageList(totalPages,currentPage,paginationSize).forEach(item=>{
            $("<li>").addClass("page-item").addClass(item ? "current-page" : "dots").toggleClass("active",item===currentPage).append($("<a>").addClass("page-link")
            .attr({href:"javascript:void(0)"}).text(item || "...")).insertBefore(".next-page");
        });

        $(".previous-page").toggleClass("disabled",currentPage===1);
        $(".next-page").toggleClass("disabled",currentPage===totalPages);
        return true;
    }
    $(".pagination").append(
        $("<li>").addClass("page-item").addClass("previous-page").append($("<a>").addClass("page-link").attr({href:"javascript:void(0)"}).text("Previous")),
        $("<li>").addClass("page-item").addClass("next-page").append($("<a>").addClass("page-link").attr({href:"javascript:void(0)"}).text("Next"))
    );
    $(".line-content").show();
    showPage(1);

    $(document).on("click",".pagination li.current-page:not(.active)",function(){
        return showPage(+$(this).text());
    });

    $(".next-page").on("click",function(){
        return showPage(currentPage+1);
    });
    $(".previous-page").on("click",function(){
        return showPage(currentPage-1);
    });
});
	