<?php

include('security.php');
include('includes/header.php'); 
include('includes/navbar.php'); 
?>


<div class="container-fluid">

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary"> EDIT Your News </h6>
        </div>
        <div class="card-body">
        <?php

            // if(isset($_POST['editReview_btn']))
            if(true)
            {
                // $id = $_POST['editReview_id'];
                 $id = '1';
                
                $query = "SELECT * FROM news WHERE id='$id' ";
                $query_run = mysqli_query($connection, $query);

                foreach($query_run as $row)
                {
                    ?>

                        <form action="code.php" method="POST">

                            <input type="hidden" name="edit_id" value="<?php echo $row['id'] ?>">

                            <div class="form-group">
                                <label> Tiêu Đề </label>
                                <input type="text" name="edit_review_title" value="<?php echo $row['title'] ?>" class="form-control" placeholder="">
                            </div>

                            <div class="form-group">
                                <label> Link Ảnh </label>
                                <input type="text" name="edit_image_link" value="<?php echo $row['image'] ?>" class="form-control" placeholder="Link Ảnh">
                            </div>

                            <div class="form-group">
                                <label> Bài Viết </label>
                                <textarea name="edit_editor" id="editor" rows="10" cols="80">
                                <?php echo $row['content']; ?>
                                </textarea>
                            </div>

                            <a href="review_list.php" class="btn btn-danger"> CANCEL </a>
                            <button type="submit" name="updateReview_btn1" class="btn btn-primary"> Update </button>

                        </form>
                        <?php
                }
            }

            

        ?>
        </div>
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
<?php
include('includes/scripts.php');
include('includes/footer.php');
?>