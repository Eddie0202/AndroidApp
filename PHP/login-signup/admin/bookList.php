<?php

include('security.php');
include('includes/header.php'); 
include('includes/navbar.php'); 
?>


<div class="modal fade" id="addadminprofile" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Thêm phim</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="code.php" method="POST" enctype="multipart/form-data">

        <div class="modal-body">

            <div class="form-group">
                <label> Title </label>
                <input type="text" name="title" class="form-control" placeholder="">
            </div>
            <div class="form-group">
                <label>Description</label>
                <input type="text" name="description" class="form-control" placeholder="">
            </div>
            <div class="form-group">
                <label>Director</label>
                <input type="text" name="director" class="form-control" placeholder="">
            </div>
            <div class="form-group">
                <label>Rating</label>
                <input type="text" name="rating" class="form-control" placeholder="">
            </div>
            <div class="form-group">
                <label>length</label>
                <input type="text" name="length" class="form-control" placeholder="">
            </div>
            <div class="form-group">
                <label>image link</label>
                <input type="file" name="image" class="form-control" placeholder="">
            </div>

        
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
            <button type="submit" name="addMovie_btn" class="btn btn-primary">Thêm</button>
        </div>
      </form>

    </div>
  </div>
</div>


<div class="container-fluid">

<!-- DataTales Example -->
<div class="card shadow mb-4">
  <div class="card-header py-3">
    <h6 class="m-0 font-weight-bold text-primary">Thông tin phim 
            <form action="addMovie.php">
                <button type="submit" class="btn btn-primary">
                  Thêm phim
                </button>
            </form>
    </h6>
  </div>

  <div class="card-body">

    <?php 
      if (isset($_SESSION['status']) !='') {
        # code...
        echo '<h3 class="bg-success">'.$_SESSION['status'].'</h3>';
        unset($_SESSION['status']);
      }

     ?>

    <div class="table-responsive">
        <?php 

            $query = "select m.id_movies, m.director, m.description, m.title, m.length, m.rating, m.imagelink, group_concat(tl.tag, '') as tags
                from ((movies m
                inner join movies_tags t
                on m.id_movies = t.movies_id) 
                inner join tags tl
                on t.tags_id = tl.id)
                group by m.id_movies";
            $query_run = mysqli_query($connection, $query);
          ?>


      <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
        <thead>
          <tr>
            <th> ID </th>
            <th> Title </th>
            <th> Image</th>
            <th> Director </th>
            <th> Rating </th>
            <th> Description </th>
            <th> Length</th>
            <th> Tags</th>
            <th>EDIT </th>
          </tr>
        </thead>
        <tbody>
                        <?php
                        if(mysqli_num_rows($query_run) > 0)        
                        {
                            while($row = mysqli_fetch_assoc($query_run))
                            {
                        ?>
                            <tr>
                                <td><?php  echo $row['id_movies']; ?></td>
                                <td><?php  echo $row['title']; ?></td>
                                <?php 
                                if (strpos($row['imagelink'], 'https') !== false) {
                                ?>
                                
                                 <td><img src="<?php  echo $row['imagelink']; ?>" alt="" height=100% width=100%></img></td>
                                <?php 
                                }
                                else{
                                 ?>
                                 <td><img src="../<?php  echo $row['imagelink']; ?>" alt="" height=100% width=100%></img></td>
                               
                            <?php } ?>
                                <td><?php  echo $row['director']; ?></td>
                                <td><?php  echo $row['rating']; ?></td>
                                <td>
                                    <?php  echo $row['description']; ?>     
                                </td>
                                <td><?php  echo $row['length']; ?></td>
                                <td><?php  echo $row['tags']; ?></td>
                                <td>
                                    <form action="bookList_edit.php" method="post">
                                        <input type="hidden" name="edit_id" value="<?php echo $row['id_movies']; ?>">
                                        <button type="submit" name="edit_btn" class="btn btn-success"> EDIT</button>
                                    </form>
                                </td>
                            </tr>
                        <?php
                            } 
                        }
                        else {
                            echo "No Record Found";
                        }
                        ?>
                    </tbody>
      </table>

    </div>
  </div>
</div>

</div>
<!-- /.container-fluid -->

<?php
include('includes/scripts.php');
include('includes/footer.php');
?>