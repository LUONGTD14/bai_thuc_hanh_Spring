Heelo world !

1, Mô tả về thiết kế hệ thống của project hotelBK:
  - Project sử dụng ngôn ngử lập trình Java và framework Spring (SpringMVC và Spring Boot) để xây dựng hệ thống phía Bakcend.
  - Về phía Frontend, project sử dụng HTML, CSS và ngôn ngữ lập trình Javascript cùng với đó là các thư viện Bootstrap và JQuery
    để xây dựng giao diện cho ứng dụng.
  - Về cơ sở dữ liệu, project sử dụng hệ quản trị cơ sở dữ liệu MySql phiên bản 8.0.26 để lưu trữ dữ liệu của ứng dụng.

2, Các chức năng chính của ứng dụng:
   - Các chức năng phía khách đặt phòng:
     + Đăng kí tài khoản người dùng và đăng nhập vào hệ thống.
     + Thay đổi thông tin tài khoản của mình và thay đổi mật khẩu mới nếu cần thiết.
     + Yêu cầu hệ thống gửi mail để cập nhật lại mật khẩu trong tường hợp quên mật khẩu.
     + Tìm kiếm danh sách các phòng theo giá phòng, loại phòng, ngày check-in và check-out.
     + Sắp xếp danh sách phòng theo tên (A-Z hoặc Z-A) và giá (cao -thấp hoặc thấp -cao).
     + Xem chi tiết phòng muốn đặt và đặt phòng.
     + Chỉnh sửa thông tin ngày check-in và check-out của phòng mình vừa đặt.
     + Chuyển đổi ngôn ngữ Tiếng Anh và Tiếng Việt trên giao diện của hệ thống.
     + Gủi mail liên hệ cho Admin của hệ thống.
  - Các chức năng phía người quản trị (Admin) của hệ thống:
     + Đăng nhập vào hệ thống và được điều hướng trực tiếp đến trang admin của hệ thống.
     + Chỉnh sửa thông tin tài khoản của mình và cập nhật mật khẩu nếu cần.
     + Xem thông tin danh sách các phòng hiện đang có của khách sạn.
     + Chỉnh sửa thông tin, xóa hoặc thêm mới phòng cho khách sạn.
     + Đặt phòng trực tiếp cho khách.
     + Xem, chỉnh sửa thông tin hoặc xóa các tài khoản người dùng của khách hàng.
     + Thêm các dịch vụ cho các phòng đang được đặt.
     + Xem danh sách khách và phòng đang được đặt.
     + Xuất file PDF hóa đơn cho khách đặt phòng.

3, Đóng góp của các thành viên trong nhóm:
   + Nguyễn Hoài Linh - B19DCCN375 (Nhóm trưởng) :
      + Thiết kế và code giao diện.
      + Thiết kế database.
      + Đăng nhập vào hệ thống và được điều hướng trực tiếp đến trang admin của hệ thống.
      + Chỉnh sửa thông tin tài khoản của mình và cập nhật mật khẩu nếu cần.
      + Xem thông tin danh sách các phòng hiện đang có của khách sạn.
      + Chỉnh sửa thông tin, xóa hoặc thêm mới phòng cho khách sạn.
      + Đặt phòng trực tiếp cho khách.
      + Xem, chỉnh sửa thông tin hoặc xóa các tài khoản người dùng của khách hàng.
      + Thêm các dịch vụ cho các phòng đang được đặt.
      + Xem danh sách khách và phòng đang được đặt.
      + Xuất file PDF hóa đơn cho khách đặt phòng.
      
   + Lê Ngọc Phương - B19DCCN509:
      - Đăng kí tài khoản người dùng và đăng nhập vào hệ thống.
      - Thay đổi thông tin tài khoản của mình và thay đổi mật khẩu mới nếu cần thiết.
      - Tìm kiếm danh sách các phòng theo giá phòng, loại phòng, ngày check-in và check-out.
      - Sắp xếp danh sách phòng theo tên (A-Z hoặc Z-A) và giá (cao -thấp hoặc thấp -cao).
     
   + Nguyễn Tiến Đại - B19DCCN161:
      - Xem chi tiết phòng muốn đặt và đặt phòng.
      - Chỉnh sửa thông tin ngày check-in và check-out của phòng mình vừa đặt.
      - Xem thông tin danh sách các phòng hiện đang có của khách sạn.
      - Chỉnh sửa thông tin, xóa hoặc thêm mới phòng cho khách sạn.
      - Đặt phòng trực tiếp cho khách.

4, Các màn hình Demo ứng dụng hotelBK:
   - Phía khách đặt phòng: 
     + Trang chủ:
     ![image](https://user-images.githubusercontent.com/90165371/170810795-22ac154d-ac0f-4813-ab84-168a8580093e.png)
     + Danh sách phòng:
     ![image](https://user-images.githubusercontent.com/90165371/170810822-6f4dc3da-b1c0-4d60-9468-d96732bc50d9.png)
     + Đặt phòng:
     ![image](https://user-images.githubusercontent.com/90165371/170810986-e99f6538-1ec9-4ccd-9358-7ff6d27ab536.png)
     + Đăng kí tài khoản:
     ![image](https://user-images.githubusercontent.com/90165371/170810851-d01e98a4-ef29-43d6-b24b-319000f75f09.png)
     + Đăng nhập:
     ![image](https://user-images.githubusercontent.com/90165371/170810938-b42db125-3e0f-4596-92b7-c38dee368a29.png)
     + Chỉnh sửa thông tin phòng đã đặt:
     ![image](https://user-images.githubusercontent.com/90165371/170811055-dd2c321a-a96c-41c3-9ba4-dba54be38995.png)
     +Chỉnh sửa thông tin tài khoản:
     ![image](https://user-images.githubusercontent.com/90165371/170811161-4987f4c6-0572-42db-9482-d1f59585c071.png)
     + Quên mật khẩu: 
     ![image](https://user-images.githubusercontent.com/90165371/170811090-a98cae26-89d3-4d5c-b9bb-5614239811b2.png)
     + Liên hệ:
     ![image](https://user-images.githubusercontent.com/90165371/170811067-e0ae7a1b-48bf-4441-a9b8-6c9fe6d546f0.png)

   - Phía Admin:
     + Trang chủ:  
     ![image](https://user-images.githubusercontent.com/90165371/170811182-4d2667f0-7d99-449d-af43-13604a7afeda.png)
     + Danh sách phòng:
     ![image](https://user-images.githubusercontent.com/90165371/170814153-d9897930-d138-4a9a-a163-ad2706d81437.png)
     + Thêm, sửa thông tin phòng:
     ![image](https://user-images.githubusercontent.com/90165371/170814226-ff416484-320d-46e9-93c9-1f1f339d3e16.png)
     + Thêm dịch vụ cho các phòng:
     ![image](https://user-images.githubusercontent.com/90165371/170814269-e6dbf849-9111-4994-9215-f6fb14286170.png)
     + Thông tin hóa đơn:
     ![image](https://user-images.githubusercontent.com/90165371/170814318-8f4377bb-f402-4e45-861e-8436ab598c57.png)
     + File PDF hóa đơn:
     ![image](https://user-images.githubusercontent.com/90165371/170814403-a7ceb922-5ff7-4187-af93-353cfe175725.png)
     + Thông tin tài khoản người dùng:
     ![image](https://user-images.githubusercontent.com/90165371/170814468-cab6be57-0cb5-43a7-92be-3522b84bcaca.png)


   
