USE [QuanLyCoffe]
GO
/****** Object:  Table [dbo].[DANHSACHBAN]    Script Date: 10/25/2019 11:13:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DANHSACHBAN](
	[MaBan] [int] IDENTITY(1,1) NOT NULL,
	[TenBan] [nvarchar](50) NULL,
	[TrangThai] [nvarchar](50) NULL,
 CONSTRAINT [PK_DANHSACHBAN] PRIMARY KEY CLUSTERED 
(
	[MaBan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[GOIMON]    Script Date: 10/25/2019 11:13:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GOIMON](
	[MaGoiMon] [int] IDENTITY(1,1) NOT NULL,
	[MaBan] [int] NOT NULL,
	[MaMon] [int] NOT NULL,
	[SoLuong] [int] NULL,
	[ThanhTien]  AS ([SoLuong]*[DonGia]),
	[DonGia] [decimal](9, 0) NULL,
 CONSTRAINT [PK_GOIMON] PRIMARY KEY CLUSTERED 
(
	[MaGoiMon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HOADON]    Script Date: 10/25/2019 11:13:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HOADON](
	[MaHD] [int] NOT NULL,
	[MaBan] [int] NULL,
	[MaMon] [int] NULL,
	[MaNV] [int] NULL,
	[NgayThanhToan] [datetime] NULL,
	[TongTien] [money] NULL,
	[SoLuong] [int] NULL,
	[DonGia] [decimal](9, 2) NULL,
	[ThanhTien]  AS ([DonGia]*[SoLuong]),
 CONSTRAINT [PK_HOADON] PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[LOAIMON]    Script Date: 10/25/2019 11:13:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOAIMON](
	[MaLoaiMon] [int] IDENTITY(1,1) NOT NULL,
	[TenLoaiMon] [nvarchar](50) NULL,
 CONSTRAINT [PK_LOAIMON_1] PRIMARY KEY CLUSTERED 
(
	[MaLoaiMon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[MONAN]    Script Date: 10/25/2019 11:13:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MONAN](
	[MaMon] [int] IDENTITY(1,1) NOT NULL,
	[MaLoaiMon] [int] NOT NULL,
	[TenMon] [nvarchar](50) NULL,
	[DonGia] [decimal](9, 0) NULL,
	[HinhAnh] [varchar](50) NULL,
 CONSTRAINT [PK_MONAN_1] PRIMARY KEY CLUSTERED 
(
	[MaMon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 10/25/2019 11:13:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[MaNV] [int] IDENTITY(1,1) NOT NULL,
	[HoTenNV] [nvarchar](50) NULL,
	[TenDN] [varchar](50) NULL,
	[MatKhau] [varchar](50) NULL,
	[HeThong] [nvarchar](50) NULL,
 CONSTRAINT [PK_NHANVIEN] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[THONGKEDOANHTHU]    Script Date: 10/25/2019 11:13:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[THONGKEDOANHTHU](
	[MaTKDT] [int] IDENTITY(1,1) NOT NULL,
	[NgayThanhToan] [date] NULL,
	[TongTien] [decimal](9, 0) NULL,
 CONSTRAINT [PK_ThongKeDoanhThu] PRIMARY KEY CLUSTERED 
(
	[MaTKDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[DANHSACHBAN] ON 

INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (1, N'Bàn 1', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (2, N'Bàn 2', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (3, N'Bàn 3', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (4, N'Bàn 4', N'Trông')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (5, N'Bàn 5', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (6, N'Bàn 6', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (7, N'Bàn 7', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (8, N'Bàn 8', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (9, N'Bàn 9', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (10, N'Bàn 10', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (21, N'Bàn 11', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (22, N'Bàn 12', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (23, N'Bàn 13', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (24, N'Bàn 14', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (25, N'Bàn 15', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (26, N'Bàn 16', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (27, N'Bàn 17', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (28, N'Bàn 18', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (29, N'Bàn 19', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (30, N'Bàn 20', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (31, N'Bàn 21', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (32, N'Bàn 22', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (33, N'Bàn 23', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (1002, N'Bàn 24', N'Trống')
INSERT [dbo].[DANHSACHBAN] ([MaBan], [TenBan], [TrangThai]) VALUES (1003, N'Bàn 25', N'Trống')
SET IDENTITY_INSERT [dbo].[DANHSACHBAN] OFF
SET IDENTITY_INSERT [dbo].[LOAIMON] ON 

INSERT [dbo].[LOAIMON] ([MaLoaiMon], [TenLoaiMon]) VALUES (1, N'Cà phê')
INSERT [dbo].[LOAIMON] ([MaLoaiMon], [TenLoaiMon]) VALUES (2, N'Sinh tố')
INSERT [dbo].[LOAIMON] ([MaLoaiMon], [TenLoaiMon]) VALUES (3, N'Món ăn vặt')
INSERT [dbo].[LOAIMON] ([MaLoaiMon], [TenLoaiMon]) VALUES (4, N'Nước giải khát')
SET IDENTITY_INSERT [dbo].[LOAIMON] OFF
SET IDENTITY_INSERT [dbo].[MONAN] ON 

INSERT [dbo].[MONAN] ([MaMon], [MaLoaiMon], [TenMon], [DonGia], [HinhAnh]) VALUES (1, 1, N'Cà phê đá', CAST(15000 AS Decimal(9, 0)), N'ca_phe_da.jpg')
INSERT [dbo].[MONAN] ([MaMon], [MaLoaiMon], [TenMon], [DonGia], [HinhAnh]) VALUES (2, 2, N'Sinh tố dâu', CAST(30000 AS Decimal(9, 0)), N'sinh_to_dau.jpg')
INSERT [dbo].[MONAN] ([MaMon], [MaLoaiMon], [TenMon], [DonGia], [HinhAnh]) VALUES (3, 3, N'Bắp xào', CAST(20000 AS Decimal(9, 0)), N'bap_xao.jpg')
INSERT [dbo].[MONAN] ([MaMon], [MaLoaiMon], [TenMon], [DonGia], [HinhAnh]) VALUES (4, 4, N'7UP', CAST(15000 AS Decimal(9, 0)), N'7UP.jpg')
INSERT [dbo].[MONAN] ([MaMon], [MaLoaiMon], [TenMon], [DonGia], [HinhAnh]) VALUES (5, 1, N'Cà phê sữa đá', CAST(20000 AS Decimal(9, 0)), N'ca_phe_sua_da.jpg')
INSERT [dbo].[MONAN] ([MaMon], [MaLoaiMon], [TenMon], [DonGia], [HinhAnh]) VALUES (6, 2, N'Sinh tố mít', CAST(30000 AS Decimal(9, 0)), N'sinh_to_mit.jpg')
INSERT [dbo].[MONAN] ([MaMon], [MaLoaiMon], [TenMon], [DonGia], [HinhAnh]) VALUES (7, 3, N'Xúc xích đức', CAST(40000 AS Decimal(9, 0)), N'xuc_xich_duc.jpg')
INSERT [dbo].[MONAN] ([MaMon], [MaLoaiMon], [TenMon], [DonGia], [HinhAnh]) VALUES (8, 4, N'Pepsi', CAST(15000 AS Decimal(9, 0)), N'pepsi.jpg')
SET IDENTITY_INSERT [dbo].[MONAN] OFF
SET IDENTITY_INSERT [dbo].[NHANVIEN] ON 

INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [TenDN], [MatKhau], [HeThong]) VALUES (1, N'Vũ Minh Lượng', N'MinhLuongVu', N'23021998', N'PC')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [TenDN], [MatKhau], [HeThong]) VALUES (2, N'Phạm Văn Trung', N'TrungPham', N'1', N'Android')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [TenDN], [MatKhau], [HeThong]) VALUES (3, N'Vũ Minh Lượng1', N'MinhLuongVu1', N'23021998', N'Android')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [TenDN], [MatKhau], [HeThong]) VALUES (4, N'Vũ Minh Lượng2', N'MinhLuongVu2', N'23021998', N'Android')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [TenDN], [MatKhau], [HeThong]) VALUES (5, N'Vũ Minh Lượng3', N'MinhLuongVu3', N'23021998', N'Android')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [TenDN], [MatKhau], [HeThong]) VALUES (6, N'Vũ Minh Lượng4', N'MinhLuongVu4', N'23021998', N'Android')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [TenDN], [MatKhau], [HeThong]) VALUES (7, N'Vũ Minh Lượng5', N'MinhLuongVu5', N'23021998', N'Android')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [TenDN], [MatKhau], [HeThong]) VALUES (8, N'Vũ Minh Lượng6', N'MinhLuongVu6', N'23021998', N'Android')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [TenDN], [MatKhau], [HeThong]) VALUES (9, N'Vũ Minh Lượng7', N'MinhLuongVu7', N'23021998', N'Android')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [TenDN], [MatKhau], [HeThong]) VALUES (10, N'Vũ Minh Lượng8', N'MinhLuongVu8', N'23021998', N'Android')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [TenDN], [MatKhau], [HeThong]) VALUES (11, N'Vũ Minh Lượng9', N'MinhLuongVu9', N'23021998', N'Android')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [TenDN], [MatKhau], [HeThong]) VALUES (12, N'Vũ Minh Lượng10', N'MinhLuongVu10', N'23021998', N'Android')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [TenDN], [MatKhau], [HeThong]) VALUES (13, N'Vũ Minh Lượng11', N'MinhLuongVu11', N'23021998', N'Android')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [TenDN], [MatKhau], [HeThong]) VALUES (14, N'Vũ Minh Lượng12', N'MinhLuongVu12', N'23021998', N'Android')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [TenDN], [MatKhau], [HeThong]) VALUES (15, N'Vũ Minh Lượng13', N'MinhLuongVu13', N'23021998', N'Android')
SET IDENTITY_INSERT [dbo].[NHANVIEN] OFF
SET IDENTITY_INSERT [dbo].[THONGKEDOANHTHU] ON 

INSERT [dbo].[THONGKEDOANHTHU] ([MaTKDT], [NgayThanhToan], [TongTien]) VALUES (1, CAST(N'2019-10-23' AS Date), CAST(80000 AS Decimal(9, 0)))
INSERT [dbo].[THONGKEDOANHTHU] ([MaTKDT], [NgayThanhToan], [TongTien]) VALUES (4, CAST(N'2019-10-25' AS Date), CAST(400000 AS Decimal(9, 0)))
INSERT [dbo].[THONGKEDOANHTHU] ([MaTKDT], [NgayThanhToan], [TongTien]) VALUES (5, CAST(N'2019-10-27' AS Date), CAST(320000 AS Decimal(9, 0)))
INSERT [dbo].[THONGKEDOANHTHU] ([MaTKDT], [NgayThanhToan], [TongTien]) VALUES (6, CAST(N'2019-10-24' AS Date), CAST(90000 AS Decimal(9, 0)))
SET IDENTITY_INSERT [dbo].[THONGKEDOANHTHU] OFF
ALTER TABLE [dbo].[GOIMON]  WITH CHECK ADD  CONSTRAINT [FK_GOIMON_DANHSACHBAN] FOREIGN KEY([MaBan])
REFERENCES [dbo].[DANHSACHBAN] ([MaBan])
GO
ALTER TABLE [dbo].[GOIMON] CHECK CONSTRAINT [FK_GOIMON_DANHSACHBAN]
GO
ALTER TABLE [dbo].[GOIMON]  WITH CHECK ADD  CONSTRAINT [FK_GOIMON_MONAN1] FOREIGN KEY([MaMon])
REFERENCES [dbo].[MONAN] ([MaMon])
GO
ALTER TABLE [dbo].[GOIMON] CHECK CONSTRAINT [FK_GOIMON_MONAN1]
GO
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD  CONSTRAINT [FK_HOADON_DANHSACHBAN] FOREIGN KEY([MaBan])
REFERENCES [dbo].[DANHSACHBAN] ([MaBan])
GO
ALTER TABLE [dbo].[HOADON] CHECK CONSTRAINT [FK_HOADON_DANHSACHBAN]
GO
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD  CONSTRAINT [FK_HOADON_MONAN] FOREIGN KEY([MaMon])
REFERENCES [dbo].[MONAN] ([MaMon])
GO
ALTER TABLE [dbo].[HOADON] CHECK CONSTRAINT [FK_HOADON_MONAN]
GO
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD  CONSTRAINT [FK_HOADON_NHANVIEN] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
ALTER TABLE [dbo].[HOADON] CHECK CONSTRAINT [FK_HOADON_NHANVIEN]
GO
ALTER TABLE [dbo].[MONAN]  WITH CHECK ADD  CONSTRAINT [FK_MONAN_LOAIMON] FOREIGN KEY([MaLoaiMon])
REFERENCES [dbo].[LOAIMON] ([MaLoaiMon])
GO
ALTER TABLE [dbo].[MONAN] CHECK CONSTRAINT [FK_MONAN_LOAIMON]
GO
