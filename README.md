# CS211-641 Project

## รายชื่อสมาชิก
* (6410401035) นางสาวชนิษฐา ลีวงศ์เจริญ (iamnitta)
  * ออกแบบ UI 
  * 12.5 ผู้ใช้โปรแกรมสามารถปรับเปลี่ยนTheme ของ Application ได้ (UI และ ไฟล์ css)
  * 16.2 หน้ารวมเรื่องร้องเรียนของนักเรียนทั้งหมดจากทุกหมวดหมู่ (โชว์ข้อมูล)
  



* (6410406703) นางสาวนภสมล ศิริบรรจง (lemonstonex)
  * ผู้ใช้งานสามารถเปลี่ยนรหัสผ่านของตนเองได้
  * 12.5 ผู้ใช้โปรแกรมสามารถปรับเปลี่ยนTheme ของ Application ได้ (UI)
  * 14.2 หน้าแสดงรายชื่อของผู้ใช้ระบบในหน้าผู้ดูแลระบบ
  * 14.3 หน้าแสดงรายการของรายงานความไม่เหมาะสมของผู้ใช้ระบบ (โชว์ข้อมูล)
  * 15.การสร้างบัญชีของผู้ใช้ระบบ (การอ่านไฟล์ เขียนไฟล์ และ model class)



* (6410406771) นายปัณณทัต ด้วงแค (Punnatud)
  * การอัพโหลดรูปภาพ
  * 16.2 หน้ารวมเรื่องร้องเรียนของนักเรียนทั้งหมดจากทุกหมวดหมู่ (แยกเรื่องร้องเรียนตามลำดับ,หัวข้อ ที่สนใจได้)
  * 17.2 หน้าจัดการเรื่องร้องเรียนของเจ้าหน้าที่ สามารถจัดการเรื่องร้องเรียนได้
  * 16.5 หน้ารวมเรื่องร้องเรียนเฉพาะเรื่องร้องเรียนที่ผู้ใช้ระบบที่กําลัง login อยู่




* (6410406924) นางสาวอาทิตยา พรมเลิศ (atitaya03)
  * 14.3 หน้าแสดงรายการของรายงานความไม่เหมาะสมของผู้ใช้ระบบ (การระงับบัญชีผู้ใช้ การลบเนื้อหา และการคืนสิทธิ์)
  * 16.1 แจ้งเรื่องร้องเรียนภายในมหาวิทยาลัย
  * 16.4 หน้ารายละเอียดของเรื่องร้องเรียน ที่สามารถโหวต และมีส่วนที่ให้ผู้ใช้ระบบแจ้งรายงานความไม่เหมาะสมของเนื้อหาหรือของผู้ใช้ระบบ และผู้ดูแลระบบสามารถจัดการได้ 
  
  
## วิธีการติดตั้งหรือรันโปรแกรม
  * สามารถเปิดโปรแกรมผ่านทาง jar ไฟล์ ได้โดยตรง ที่ directory **Release**


## การวางโครงสร้างไฟล์
* ###  โฟล์เดอร์ data
  * โฟล์เดอร์ csv เก็บไฟล์ .csv ทั้งหมด
      * ComplaintData.csv เก็บเรื่องร้องเรียนของนิสิตที่รายงานเข้ามา
      * userData.csv เก็บข้อมูลของผู้ใช้ระบบ
      * reportedAccount.csv เก็บข้อมูลของการทำการรายงานความไม่เหมาะสมเกี่ยวกับผู้ใช้งาน
      * reportedComplaint.csv เก็บข้อมูลของการทำการรายงานความไม่เหมาะสมเกี่ยวกับเรื่องร้องเรียน
  * โฟล์เดอร์ profileUsers จะเก็บรูปโปรไฟล์ของผู้ใช้งาน รวมถึง default profile สำหรับรูปโฟล์ไฟล์ตั้งต้น




## ตัวอย่างข้อมูลผู้ใช้ระบบ
* (Student) (Username: nokkok) (Password: 2435)
* (Student) (Username: lemon) (Password: 1234512345)
* (Student) (Username: kantima) (Password: Kanjanapas)
* (Staff) (Username: cleaner1) (Password: cleaner1)
* (Staff) (Username: building1) (Password: building1)
* (Staff) (Username: road1) (Password: road1)
* (Admin) (Username: admin ) (Password: 13)



## สรุปสิ่งที่พัฒนาแต่ละครั้งที่นำเสนอความก้าวหน้าของระบบ
* ครั้งที่ 1 (12 สิงหาคม 2022)
    * ออกแบบ GUI login + credits **(Punnatud)**
    * ออกแบบ GUI Student **(lemonstonex)**
    * ออกแบบ GUI Staff **(atitaya03)**
    * ออกแบบ GUI Admin **(iamnitta)**
* ครั้งที่ 2 (9 กันยายน 2022)
    * create service package contains DataSource, AccountFileDataSource, and class Account, AccountList, staffsignup.fxml, staffSignupController **(lemonstonex)**
    * create studentreport.fxml, studentedit.fxml, StudentEditController, StudentReportController **(Punnatud)**
    * create staff.fxml, staffdetail.fxml, staffedit.fxml, StaffEditController, StaffController, StaffDetailController **(iamnitta)**
    * create signup.fxml อัพเดต controller เพื่อใช้ในการเข้าสู่ระบบและส่งข้อมูลไปในแต่ละหน้าได้ **(atitaya03)**
* ครั้งที่ 3 (30 กันยายน 2022)
    * แก้ไขหน้า UI ให้มีความสบายตามากขึ้น, ChangePasswordController, สามารถเปลี่ยนรหัสผ่านได้ **(lemonstonex)**
    * create class Complaint, ComplaintList, ComplaintFileDataSource, นิสิตสามารถแจ้งเรื่องร้องเรียนได้ **(atitaya03)**
    * เพิ่ม method สามารถอัพโหลดรูปได้ **(Punnatud)**
    * report.fxml, ReportController **(iamnitta)**

