using QuanLyCoffe.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace QuanLyCoffe.ViewModel
{
    class LoginViewModel : BaseViewModel
    {
        public bool IsLogin { get; set; }
        private string _TenDN;
        public string TenDN { get => _TenDN; set { _TenDN = value; OnPropertyChanged(); } }
        private string _MatKhau;
        public string MatKhau { get => _MatKhau; set { _MatKhau = value; OnPropertyChanged(); } }

        public ICommand CloseCommand { get; set; }
        public ICommand LoginCommand { get; set; }
        public ICommand PasswordChangedCommand { get; set; }

        public LoginViewModel()
        {
            IsLogin = false;
            MatKhau  = "";
            TenDN = "";
            LoginCommand = new RelayCommand<Window>((p) => { return true; }, (p) => { Login(p); });
            CloseCommand = new RelayCommand<Window>((p) => { return true; }, (p) => { p.Close(); });
            PasswordChangedCommand = new RelayCommand<PasswordBox>((p) => { return true; }, (p) => { MatKhau = p.Password; });
        }

        void Login(Window p)
        {
            if (p == null)
                return;
            var accCount = DataProvider.Ins.DB.NHANVIENs.Where(x => x.TenDN == TenDN && x.MatKhau == MatKhau && x.HeThong=="PC").Count();
            if (accCount > 0) 
            {
                
                IsLogin = true;
                p.Close();
            }
            else
            {
                IsLogin = false;
                MessageBox.Show("Sai tài khoản hoặc mật khẩu!","Thông báo!");
            }
        }
    }
}
