<?php

include('security.php');
include('includes/header.php'); 
include('includes/navbar.php'); 
?>


<div class="container-fluid">


    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary"> Thêm bài đánh giá </h6>
        </div>
        <?php 
      if (isset($_SESSION['status']) !='') {
        # code...
        echo '<h3 class="bg-success">'.$_SESSION['status'].'</h3>';
        unset($_SESSION['status']);
      }

     ?>
        <div class="card-body">
                        <form action="code.php" method="POST" enctype="multipart/form-data">

                            <div class="form-group">
                                <label> Tiêu Đề </label>
                                <input type="text" name="review_title" class="form-control" placeholder="">
                            </div>

                            <div class="form-group">
                                <label>image</label>
                                <input type="file" name="image" class="form-control" placeholder="">
                            </div>

                            <div class="form-group">
                                <label> Title </label>
                                <textarea name="editor" id="editor" rows="10" cols="80">
                                
                                </textarea>
                            </div>

                            
                            <a href="index.php" class="btn btn-danger"> Hủy </a>
                            <button type="submit" name="addReview_btn" class="btn btn-primary"> Thêm </button>

                        </form>

                        
        </div>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.jquery.min.js"></script>
<script src="../ckeditor/ckeditor.js"></script>
<script>
    CKEDITOR.replace( 'editor', {
        height: 500
    } );
</script>
<script type="">
    $(".chosen-select").chosen({
  no_results_text: "Oops, nothing found!"
})
</script>


<?php
include('includes/scripts.php');
include('includes/footer.php');
?>