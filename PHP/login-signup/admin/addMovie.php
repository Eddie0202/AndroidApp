<?php

include('security.php');
include('includes/header.php'); 
include('includes/navbar.php'); 
?>


<div class="container-fluid">

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary"> Thêm phim </h6>
        </div>
        <div class="card-body">

                        <form action="code.php" method="POST" enctype="multipart/form-data">
                            <div class="form-group">
                                <label> Title </label>
                                <input type="text" name="title" value="" class="form-control"
                                    placeholder="Enter Title">
                            </div>
                            <div class="form-group">
                                <label>Director</label>
                                <input type="text" name="director" value="" class="form-control"
                                    placeholder="Enter Director">
                            </div>
                            <div class="form-group">
                                <label>Length</label>
                                <input type="text" name="length" value=""
                                    class="form-control" placeholder="Enter Length">
                            </div>
                            <div class="form-group">
                                <label>Rating</label>
                                <input type="text" name="rating" value=""
                                    class="form-control" placeholder="Enter Rating X/5">
                            </div>
                            <div class="form-group">
                                <select  data-placeholder="" multiple class="chosen-select form-control" name="tag[]">
                                    <?php 
                                    $tags_qr1 = "select *
                                                from tags";
                                    $qr1 = $tags_qr1;
                                    $qr_run1 = mysqli_query($connection, $qr1);

                                    if(mysqli_num_rows($qr_run1) > 0)        
                                    {
                                        while($nav_row = mysqli_fetch_assoc($qr_run1))
                                        {
                                            

                                    ?>
                                    <option value="<?php echo $nav_row['id'] ?>"><?php echo $nav_row['tag'] ?></option>
                                    <?php
                                        } 
                                    }
                                    else {
                                        echo "No Record Found";
                                    }
                                    ?>
                                </select>
                            </div>
                            <div class="form-group">                       
                                <label>Description</label>
                                <textarea type="text" class="form-control" name="description"></textarea>
                            </div>
                            <div class="form-group">                       
                                <label>Video Link</label>
                                <textarea type="text" class="form-control" name="movielink"></textarea>
                            </div>
                            <div class="form-group">
                                <label>image</label>
                                <input type="file" name="image" class="form-control" placeholder="">
                            </div>

                            <a href="bookList.php" class="btn btn-danger"> Hủy </a>
                            <button type="submit" name="addMovie_btn" class="btn btn-primary"> Thêm </button>

                        </form>

        </div>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.jquery.min.js"></script>
<script type="">
    $(".chosen-select").chosen({
  no_results_text: "Oops, nothing found!"
})
</script>


<?php
include('includes/scripts.php');
include('includes/footer.php');
?>