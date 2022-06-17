<?php

include('security.php');
include('includes/header.php'); 
include('includes/navbar.php'); 
?>



<div class="container-fluid">

<!-- DataTales Example -->
<div class="card shadow mb-4">
  <div class="card-header py-3">
    <h6 class="m-0 font-weight-bold text-primary">Thông tin hóa đơn
<!--             <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addadminprofile">
              Thêm quản trị viên
            </button> -->
    </h6>
  </div>

  <div class="card-body">


    <div class="table-responsive">
        <?php 

            $query = "SELECT * FROM bills";
            $query_run = mysqli_query($connection, $query);
          ?>


      <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
        <thead>
          <tr>
            <th> ID </th>
            <th> Username </th>
            <th> Total </th>
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
                                <td><?php  echo $row['id']; ?></td>
                                <td><?php  echo $row['c_name']; ?></td>
                                <td><?php  echo $row['total']; ?>.000VND</td>
                               
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