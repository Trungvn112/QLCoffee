using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;
using QuanLyCoffe.Model;
using System.Windows;
using System.Collections.ObjectModel;
using Microsoft.Win32;
using System.IO;


namespace QuanLyCoffe.ViewModel
{
    public class MainViewModel : BaseViewModel
    {
        public bool Isloaded = false;
        public ICommand LoadedWindowCommand { get; set; }
        // mọi thứ xử lý sẽ nằm trong này
        public MainViewModel()
        {/*
            LoadedWindowCommand = new RelayCommand<Window>((p) => { return true; }, (p) => {
                Isloaded = true;
                if (p == null)
                    return;
                p.Hide();
                Login login = new Login();
                login.ShowDialog();

                if (login.DataContext == null)
                    return;
                var loginVM = login.DataContext as LoginViewModel;

                if (loginVM.IsLogin)
                {
                    p.Show();
                    LoadDanhSachThongKeDoanhThu();
                    LoadDanhSachBan();
                    LoadDanhSachNhanVien();
                    LoadDanhSachLoaiMon();
                    LoadDanhSachMonAn();
                    
                }
                else
                {
                    p.Close();
                }
            }
              );*/
            LoadDanhSachThongKeDoanhThu();
            LoadDanhSachBan();
            LoadDanhSachNhanVien();
            LoadDanhSachLoaiMon();
            LoadDanhSachMonAn();
            ThanhToan();
            TimKiemThongKeDoanhThu();
        }


        /*Quản lý thanh toán hóa đơn*/
        private ObservableCollection<DANHSACHBAN> _DANHSACHBAN;
        public ObservableCollection<DANHSACHBAN> DANHSACHBAN { get => _DANHSACHBAN; set { _DANHSACHBAN = value; OnPropertyChanged(); } }

        private ObservableCollection<Model.GOIMON> _GOIMON;
        public ObservableCollection<Model.GOIMON> GOIMON { get => _GOIMON; set { _GOIMON = value; OnPropertyChanged(); } }

        private ObservableCollection<Model.MONAN> _MONAN;
        public ObservableCollection<Model.MONAN> MONAN { get => _MONAN; set { _MONAN = value; OnPropertyChanged(); } }


        private ObservableCollection<Model.THONGKEDOANHTHU> _THONGKEDOANHTHU;
        public ObservableCollection<Model.THONGKEDOANHTHU> THONGKEDOANHTHU { get => _THONGKEDOANHTHU; set { _THONGKEDOANHTHU = value; OnPropertyChanged(); } }

        private int _MaBan;
        public int MaBan { get => _MaBan; set { _MaBan = value; OnPropertyChanged(); } }

        private int _SoLuong;
        public int SoLuong { get => _SoLuong; set { _SoLuong = value; OnPropertyChanged(); } }


        private string _DonGia;
        public string DonGia { get => _DonGia; set { _DonGia = value; OnPropertyChanged(); } }

        private string _ThanhTien;
        public string ThanhTien { get => _ThanhTien; set { _ThanhTien = value; OnPropertyChanged(); } }

        private string _TenMon;
        public string TenMon { get => _TenMon; set { _TenMon = value; OnPropertyChanged(); } }

        public void LoadDanhSachBan()
        {
            DANHSACHBAN = new ObservableCollection<DANHSACHBAN>(DataProvider.Ins.DB.DANHSACHBANs);

        }

        private int _TongTien;
        public int TongTien { get => _TongTien; set { _TongTien = value; OnPropertyChanged(); } }
        private string _Tien;
        public string Tien { get => _Tien; set { _Tien = value; OnPropertyChanged(); } }
        private DANHSACHBAN _SelectedItem;
        public DANHSACHBAN SelectedItem
        {
            get => _SelectedItem;
            set
            {
                _SelectedItem = value;
                OnPropertyChanged();
                if (SelectedItem != null)
                {
                    TongTien = 0;
                    MaBan = SelectedItem.MaBan;
                    GOIMON = new ObservableCollection<GOIMON>(DataProvider.Ins.DB.GOIMONs.Where(x => x.MaBan == MaBan));
                    MONAN = new ObservableCollection<MONAN>(DataProvider.Ins.DB.MONANs);
                    for (int i = 0; i < GOIMON.Count; i++)
                    {
                        TongTien += int.Parse(GOIMON[i].ThanhTien.ToString());
                    }
                }

            }
        }
        public ICommand DeleteCommand { get; set; }
        public void ThanhToan()
        {
            DeleteCommand = new RelayCommand<GOIMON>((p) =>
            {

                if (SelectedItem == null)
                    return false;

                var MaBan1 = DataProvider.Ins.DB.GOIMONs.Where(x => x.MaBan == SelectedItem.MaBan);
                if (MaBan1.Count() <= 0)
                    return false;

                return true;


            }, (p) =>
            {
                if (string.IsNullOrEmpty(Tien))
                {
                    MessageBox.Show("Vui lòng nhập số tiền khách hàng đưa!", "Thông báo!");
                }
                else
                {
                    if (int.Parse(Tien) < TongTien)
                    {
                        MessageBox.Show("Vui lòng kiểm tra lại số tiền khách hàng đưa!", "Thông báo!");
                    }
                    else
                    {
                        if (int.Parse(Tien) >= TongTien)
                        {
                            GOIMON = new ObservableCollection<GOIMON>(DataProvider.Ins.DB.GOIMONs.Where(x => x.MaBan == SelectedItem.MaBan));
                            var TrangThai = DataProvider.Ins.DB.DANHSACHBANs.Where(x => x.MaBan == SelectedItem.MaBan).SingleOrDefault();
                            TrangThai.TrangThai = Convert.ToString("Trống");
                            var ngaythanhtoan = DataProvider.Ins.DB.THONGKEDOANHTHUs.Where(x => x.NgayThanhToan == DateTime.Today).SingleOrDefault();
                            if (ngaythanhtoan == null)
                            {
                                var thongkedoanhthu = new THONGKEDOANHTHU() { TongTien = TongTien, NgayThanhToan = DateTime.Today };
                                DataProvider.Ins.DB.THONGKEDOANHTHUs.Add(thongkedoanhthu);
                            }
                            else
                            {
                                ngaythanhtoan.TongTien += TongTien;
                            }
                            DataProvider.Ins.DB.GOIMONs.RemoveRange(GOIMON);
                            DataProvider.Ins.DB.SaveChanges();
                            GOIMON = null;
                            SelectedItem.MaBan = MaBan;
                            int TienDu = int.Parse(Tien) - TongTien;
                            MessageBox.Show("Thanh toán thành công!" + "\n \n" + "Số tiền trả lại cho khách hàng là: " + TienDu + " VNĐ", "Thông báo!");
                            Tien = "";
                            TongTien = 0;
                            LoadDanhSachThongKeDoanhThu();
                        }
                    }
                }

            });
        }

        /*Kết thúc quản lý thanh toán hóa đơn*/


        /*Quản lý danh sách nhân viên*/
        private ObservableCollection<NHANVIEN> _NHANVIEN;
        public ObservableCollection<NHANVIEN> NHANVIEN { get => _NHANVIEN; set { _NHANVIEN = value; OnPropertyChanged(); } }

        public void LoadDanhSachNhanVien()
        {
            NHANVIEN = new ObservableCollection<NHANVIEN>(DataProvider.Ins.DB.NHANVIENs);
        }
        /*Kết thúc quản lý danh sách nhân viên*/



        /*Quản lý danh sách loại món ăn*/
        private ObservableCollection<LOAIMON> _LOAIMON;
        public ObservableCollection<LOAIMON> LOAIMON { get => _LOAIMON; set { _LOAIMON = value; OnPropertyChanged(); } }

        public void LoadDanhSachLoaiMon()
        {
            LOAIMON = new ObservableCollection<LOAIMON>(DataProvider.Ins.DB.LOAIMONs);
        }
        /*Kết thúc quản lý danh sách loại món ăn*/


        /*Quản lý danh sách món ăn*/
        private ObservableCollection<MONAN> _MONAN1;
        public ObservableCollection<MONAN> MONAN1 { get => _MONAN1; set { _MONAN1 = value; OnPropertyChanged(); } }

        public void LoadDanhSachMonAn()
        {
            MONAN1 = new ObservableCollection<MONAN>(DataProvider.Ins.DB.MONANs);
        }
        /*Kết thúc quản lý danh sách loại món ăn*/

        /*Quản lý danh sách thống kê doanh thu*/
        private ObservableCollection<THONGKEDOANHTHU> _thongkedoanhthu;
        public ObservableCollection<THONGKEDOANHTHU> thongkedoanhthu { get => _thongkedoanhthu; set { _thongkedoanhthu = value; OnPropertyChanged(); } }

        private DateTime _NgayThongKeDoanhThu;
        public DateTime NgayThongKeDoanhThu { get => _NgayThongKeDoanhThu; set { _NgayThongKeDoanhThu = value; OnPropertyChanged(); } }

        public void LoadDanhSachThongKeDoanhThu()
        {
            thongkedoanhthu = new ObservableCollection<THONGKEDOANHTHU>(DataProvider.Ins.DB.THONGKEDOANHTHUs);
        }
        public ICommand TimKiem { get; set; }
        public void TimKiemThongKeDoanhThu()
        {
            NgayThongKeDoanhThu = DateTime.Today;
            TimKiem = new RelayCommand<THONGKEDOANHTHU>((p) =>
            {
                return true;
            }, (p) =>
            {
                DateTime d = DateTime.Parse((NgayThongKeDoanhThu.ToString()));
                var ngaythanhtoan = DataProvider.Ins.DB.THONGKEDOANHTHUs.Where(x => x.NgayThanhToan == d).SingleOrDefault();
                if (ngaythanhtoan!=null)
                {
                    thongkedoanhthu = new ObservableCollection<THONGKEDOANHTHU>(DataProvider.Ins.DB.THONGKEDOANHTHUs.Where(x=>x.NgayThanhToan==d));
                }
                else
                {
                    thongkedoanhthu = null;
                }
               
            });
            /*Kết thúc quản lý thống kê doanh thu*/
        }
    }
}

