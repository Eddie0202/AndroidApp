<?php
include('security.php');
include('includes/header.php'); 
include('includes/navbar.php'); 
?>




<div class="container-fluid">

<!-- DataTales Example -->
<div class="card shadow mb-4">
  <div class="card-header py-3">
    <h6 class="m-0 font-weight-bold text-primary">Thông tin bài đánh giá
        <form action="addReview.php">
            <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#addadminprofile">
              Thêm bài đánh giá
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

            $query = "SELECT * FROM review";
            $query_run = mysqli_query($connection, $query);
          ?>


      <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
        <thead>
          <tr>
            <th> ID </th>
            <th> title </th>
            <th> author </th>
            <th> date</th>
            <th> image</th>
            <th>EDIT </th>
            <th>DELETE </th>
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
                                <td><?php  echo $row['id_review']; ?></td>
                                <td><?php  echo $row['title']; ?></td>
                                <td><?php  echo $row['author']; ?></td>
                                <td><?php  echo $row['date']; ?></td>
                                <td><img src="<?php  echo $row['image_link']; ?>" alt="" height=50% width=50%></img></td>
                                <td>
                                    <form action="review_edit.php" method="post" enctype="multipart/form-data">
                                        <input type="hidden" name="editReview_id" value="<?php echo $row['id_review']; ?>">
                                        <button type="submit" name="editReview_btn" class="btn btn-success"> EDIT</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="code.php" method="post">
                                        <input type="hidden" name="deleteReview_id" value="<?php echo $row['id_review']; ?>">
                                        <button type="submit" name="deleteReview_btn" class="btn btn-danger"> DELETE</button>
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