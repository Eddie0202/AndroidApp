<?php

include('security.php');
include('includes/header.php'); 
include('includes/navbar.php'); 
?>


<div class="container-fluid">

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary"> Chỉnh sửa thông tin phim </h6>
        </div>
        <div class="card-body">
        <?php

            if(isset($_POST['edit_btn']))
            {
                $id = $_POST['edit_id'];
                
                $query = "select m.id_movies, m.director, m.description, m.title, m.length, m.rating, m.imagelink, group_concat(tl.tag, '') as tags
                from ((movies m
                inner join movies_tags t
                on m.id_movies = t.movies_id) 
                inner join tags tl
                on t.tags_id = tl.id)
                WHERE id_movies='$id'
                group by m.id_movies";
                $query_run = mysqli_query($connection, $query);

                foreach($query_run as $row)
                {
                    ?>

                        <form action="code.php" method="POST" enctype="multipart/form-data">

                            <input type="hidden" name="edit_id" value="<?php echo $row['id_movies'] ?>">

                            <div class="form-group">
                                <label> Title </label>
                                <input type="text" name="edit_title" value="<?php echo $row['title'] ?>" class="form-control"
                                    placeholder="Enter Title">
                            </div>
                            <div class="form-group">
                                <label>Director</label>
                                <input type="text" name="edit_director" value="<?php echo $row['director'] ?>" class="form-control"
                                    placeholder="Enter Director">
                            </div>
                            <div class="form-group">
                                <label>Length</label>
                                <input type="text" name="edit_length" value="<?php echo $row['length'] ?>"
                                    class="form-control" placeholder="Enter Length">
                            </div>
                            <div class="form-group">
                                <label>Rating</label>
                                <input type="text" name="edit_rating" value="<?php echo $row['rating'] ?>"
                                    class="form-control" placeholder="Enter Rating">
                            </div>
                            <div class="form-group">
                                <select  data-placeholder="<?php echo $row['tags'] ?>" multiple class="chosen-select form-control" name="edit_tag[]">
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
                                <textarea type="text" class="form-control" name="edit_description"><?php echo $row['description'] ?></textarea>
                            </div>

                            <div class="form-group">
                                <label>image</label>
                                <input type="file" name="edit_image" class="form-control" placeholder="">
                            </div>

                            <a href="bookList.php" class="btn btn-danger"> CANCEL </a>
                            <button type="submit" name="updateMovie_btn" class="btn btn-primary"> Update </button>

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
<script type="">
    $(".chosen-select").chosen({
  no_results_text: "Oops, nothing found!"
})
</script>


<?php
include('includes/scripts.php');
include('includes/footer.php');
?>