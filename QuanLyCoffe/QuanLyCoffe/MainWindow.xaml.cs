using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using QuanLyCoffe.Model;

namespace QuanLyCoffe
{
    
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
       
        public MainWindow()
        {
            InitializeComponent();
        }

        private void GridViewColumn_ImageFailed(object sender, ExceptionRoutedEventArgs e)
        {

        }

        private void TextBlock_TextInput(object sender, TextCompositionEventArgs e)
        {

        }

        private void TextBox_SelectedTimeChanged(object sender, RoutedPropertyChangedEventArgs<DateTime?> e)
        {

        }

        private void TabablzControl_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

        }

        private void TabablzControl_SelectionChanged_1(object sender, SelectionChangedEventArgs e)
        {

        }
    }
}
