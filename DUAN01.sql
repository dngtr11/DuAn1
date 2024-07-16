create database DUAN01
USE [DUAN01]
GO
/****** Object:  Table [dbo].[ChatLieu]    Script Date: 5/16/2024 7:26:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChatLieu](
	[IDCHATLIEU] [int] IDENTITY(1,1) NOT NULL,
	[MACHATLIEU] [varchar](10) NULL,
	[TENCHATLIEU] [nvarchar](50) NULL,
	[TRANGTHAI] [bit] NULL,
 CONSTRAINT [PK_ChatLieu] PRIMARY KEY CLUSTERED 
(
	[IDCHATLIEU] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 5/16/2024 7:26:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucVu](
	[IDCHUCVU] [int] IDENTITY(1,1) NOT NULL,
	[MACHUCVU] [varchar](10) NULL,
	[TENCHUCVU] [nvarchar](50) NULL,
	[TRANGTHAI] [bit] NULL,
 CONSTRAINT [PK_ChucVu] PRIMARY KEY CLUSTERED 
(
	[IDCHUCVU] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 5/16/2024 7:26:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[IDHOADON] [int] IDENTITY(1,1) NOT NULL,
	[MAHOADON] [varchar](10) NULL,
	[IDKHACHHANG] [int] NULL,
	[IDNHANVIEN] [int] NULL,
	[IDVOUCHER] [int] NULL,
	[IDHDCT] [int] NULL,
	[TONGTIEN] [money] NULL,
	[NGAYTHANHTOAN] [date] NULL,
	[NGAYTAO] [date] NULL,
	[NGUOITAO] [varchar](10) NULL,
	[TRANGTHAI] [bit] NULL,
	[ANTT] [bit] NULL,
 CONSTRAINT [PK_HoaDon] PRIMARY KEY CLUSTERED 
(
	[IDHOADON] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonChiTiet]    Script Date: 5/16/2024 7:26:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonChiTiet](
	[IDHDCT] [int] IDENTITY(1,1) NOT NULL,
	[IDHOADON] [int] NULL,
	[IDSPCT] [int] NULL,
	[SOLUONG] [int] NULL,
	[GIA] [money] NULL,
	[THANHTIEN] [money] NULL,
	[NGUOITAO] [varchar](50) NULL,
	[NGUOISUA] [varchar](10) NULL,
	[NGAYTAO] [date] NULL,
	[NGAYSUA] [date] NULL,
 CONSTRAINT [PK_HoaDonChiTiet] PRIMARY KEY CLUSTERED 
(
	[IDHDCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 5/16/2024 7:26:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[IDKHACHHANG] [int] IDENTITY(1,1) NOT NULL,
	[MAKHACHHANG] [varchar](10) NULL,
	[HOTEN] [nvarchar](50) NULL,
	[GIOITINH] [bit] NULL,
	[SDT] [nvarchar](10) NULL,
	[NGAYSINH] [date] NULL,
	[EMAIL] [varchar](30) NULL,
	[NGAYTAO] [date] NULL,
	[NGAYSUA] [date] NULL,
	[NGUOITAO] [varchar](10) NULL,
	[NGUOISUA] [varchar](10) NULL,
	[TRANGTHAI] [bit] NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[IDKHACHHANG] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MauSac]    Script Date: 5/16/2024 7:26:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MauSac](
	[IDMAUSAC] [int] IDENTITY(1,1) NOT NULL,
	[MAMAU] [varchar](10) NULL,
	[TENMAU] [nvarchar](50) NULL,
	[TRANGTHAI] [bit] NULL,
 CONSTRAINT [PK_MauSac] PRIMARY KEY CLUSTERED 
(
	[IDMAUSAC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 5/16/2024 7:26:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[IDNHANVIEN] [int] IDENTITY(1,1) NOT NULL,
	[MANHANVIEN] [varchar](10) NULL,
	[IDCHUCVU] [int] NULL,
	[HOTEN] [nvarchar](50) NULL,
	[NGAYSINH] [date] NULL,
	[GIOITINH] [bit] NULL,
	[DIACHI] [nvarchar](50) NULL,
	[SDT] [varchar](10) NULL,
	[EMAIL] [nvarchar](50) NULL,
	[MATKHAU] [varchar](10) NULL,
	[NGAYTAO] [date] NULL,
	[NGAYSUA] [date] NULL,
	[NGUOITAO] [varchar](10) NULL,
	[NGUOISUA] [varchar](10) NULL,
	[TRANGTHAI] [bit] NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[IDNHANVIEN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 5/16/2024 7:26:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[IDSANPHAM] [int] IDENTITY(1,1) NOT NULL,
	[MASANPHAM] [varchar](10) NULL,
	[TENSP] [nvarchar](50) NULL,
	[TRANGTHAI] [bit] NULL,
 CONSTRAINT [PK_SanPham] PRIMARY KEY CLUSTERED 
(
	[IDSANPHAM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPhamChiTiet]    Script Date: 5/16/2024 7:26:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPhamChiTiet](
	[IDSPCT] [int] IDENTITY(1,1) NOT NULL,
	[IDSANPHAM] [int] NULL,
	[IDCHATLIEU] [int] NULL,
	[IDTHUONGHIEU] [int] NULL,
	[IDMAUSAC] [int] NULL,
	[IDSIZE] [int] NULL,
	[SOLUONG] [int] NULL,
	[GIA] [money] NULL,
	[MOTA] [nvarchar](50) NULL,
	[NGUOITAO] [varchar](10) NULL,
	[NGUOISUA] [varchar](10) NULL,
	[NGAYTAO] [date] NULL,
	[NGAYSUA] [date] NULL,
	[TRANGTHAI] [bit] NULL,
 CONSTRAINT [PK_SanPhamChiTiet] PRIMARY KEY CLUSTERED 
(
	[IDSPCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Size]    Script Date: 5/16/2024 7:26:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Size](
	[IDSIZE] [int] IDENTITY(1,1) NOT NULL,
	[MASIZE] [varchar](10) NULL,
	[TENSIZE] [nvarchar](50) NULL,
	[TRANGTHAI] [bit] NULL,
 CONSTRAINT [PK_Size] PRIMARY KEY CLUSTERED 
(
	[IDSIZE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ThuongHieu]    Script Date: 5/16/2024 7:26:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThuongHieu](
	[IDTHUONGHIEU] [int] IDENTITY(1,1) NOT NULL,
	[MATHUONGHIEU] [varchar](10) NULL,
	[TENTHUONGHIEU] [nvarchar](50) NULL,
	[TRANGTHAI] [bit] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Voucher]    Script Date: 5/16/2024 7:26:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Voucher](
	[IDVOUCHER] [int] IDENTITY(1,1) NOT NULL,
	[MAVOUCHER] [varchar](10) NULL,
	[TENVOUCHER] [nvarchar](50) NULL,
	[DONTOITHIEU] [money] NULL,
	[MUCGIAMGIA] [float] NULL,
	[LOAIVC] [nvarchar](50) NULL,
	[SOLUONG] [int] NULL,
	[NGAYBATDAU] [date] NULL,
	[NGAYKETTHUC] [date] NULL,
	[NGAYTAO] [date] NULL,
	[NGAYSUA] [date] NULL,
	[NGUOITAO] [varchar](10) NULL,
	[NGUOISUA] [varchar](10) NULL,
	[TRANGTHAI] [bit] NULL,
 CONSTRAINT [PK_Voucher] PRIMARY KEY CLUSTERED 
(
	[IDVOUCHER] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[ChatLieu] ON 

INSERT [dbo].[ChatLieu] ([IDCHATLIEU], [MACHATLIEU], [TENCHATLIEU], [TRANGTHAI]) VALUES (1, N'CL01', N'vai', 1)
INSERT [dbo].[ChatLieu] ([IDCHATLIEU], [MACHATLIEU], [TENCHATLIEU], [TRANGTHAI]) VALUES (2, N'CL02', N'da', 0)
INSERT [dbo].[ChatLieu] ([IDCHATLIEU], [MACHATLIEU], [TENCHATLIEU], [TRANGTHAI]) VALUES (3, N'CL03', N'bo', 1)
SET IDENTITY_INSERT [dbo].[ChatLieu] OFF
GO
SET IDENTITY_INSERT [dbo].[ChucVu] ON 

INSERT [dbo].[ChucVu] ([IDCHUCVU], [MACHUCVU], [TENCHUCVU], [TRANGTHAI]) VALUES (1, N'CV01', N'quan ly', 1)
INSERT [dbo].[ChucVu] ([IDCHUCVU], [MACHUCVU], [TENCHUCVU], [TRANGTHAI]) VALUES (2, N'CV02', N'nhan vien', 1)
SET IDENTITY_INSERT [dbo].[ChucVu] OFF
GO
SET IDENTITY_INSERT [dbo].[HoaDon] ON 

INSERT [dbo].[HoaDon] ([IDHOADON], [MAHOADON], [IDKHACHHANG], [IDNHANVIEN], [IDVOUCHER], [IDHDCT], [TONGTIEN], [NGAYTHANHTOAN], [NGAYTAO], [NGUOITAO], [TRANGTHAI], [ANTT]) VALUES (1, N'hZNyVO', NULL, NULL, NULL, NULL, 0.0000, NULL, CAST(N'2024-05-14' AS Date), NULL, 0, 1)
INSERT [dbo].[HoaDon] ([IDHOADON], [MAHOADON], [IDKHACHHANG], [IDNHANVIEN], [IDVOUCHER], [IDHDCT], [TONGTIEN], [NGAYTHANHTOAN], [NGAYTAO], [NGUOITAO], [TRANGTHAI], [ANTT]) VALUES (2, N'9ifPQU', NULL, NULL, NULL, NULL, 0.0000, NULL, CAST(N'2024-05-14' AS Date), NULL, 0, 1)
INSERT [dbo].[HoaDon] ([IDHOADON], [MAHOADON], [IDKHACHHANG], [IDNHANVIEN], [IDVOUCHER], [IDHDCT], [TONGTIEN], [NGAYTHANHTOAN], [NGAYTAO], [NGUOITAO], [TRANGTHAI], [ANTT]) VALUES (3, N'R2GQVN', NULL, NULL, NULL, NULL, 0.0000, NULL, CAST(N'2024-05-14' AS Date), NULL, 0, 1)
INSERT [dbo].[HoaDon] ([IDHOADON], [MAHOADON], [IDKHACHHANG], [IDNHANVIEN], [IDVOUCHER], [IDHDCT], [TONGTIEN], [NGAYTHANHTOAN], [NGAYTAO], [NGUOITAO], [TRANGTHAI], [ANTT]) VALUES (4, N'O0Odvw', NULL, NULL, NULL, NULL, 0.0000, NULL, CAST(N'2024-05-14' AS Date), NULL, 0, 1)
INSERT [dbo].[HoaDon] ([IDHOADON], [MAHOADON], [IDKHACHHANG], [IDNHANVIEN], [IDVOUCHER], [IDHDCT], [TONGTIEN], [NGAYTHANHTOAN], [NGAYTAO], [NGUOITAO], [TRANGTHAI], [ANTT]) VALUES (5, N'8wytzG', NULL, NULL, NULL, NULL, 0.0000, NULL, CAST(N'2024-05-14' AS Date), NULL, 0, 1)
INSERT [dbo].[HoaDon] ([IDHOADON], [MAHOADON], [IDKHACHHANG], [IDNHANVIEN], [IDVOUCHER], [IDHDCT], [TONGTIEN], [NGAYTHANHTOAN], [NGAYTAO], [NGUOITAO], [TRANGTHAI], [ANTT]) VALUES (6, N'IbZWY9', NULL, NULL, NULL, NULL, 0.0000, NULL, CAST(N'2024-05-14' AS Date), NULL, 0, 1)
SET IDENTITY_INSERT [dbo].[HoaDon] OFF
GO
SET IDENTITY_INSERT [dbo].[KhachHang] ON 

INSERT [dbo].[KhachHang] ([IDKHACHHANG], [MAKHACHHANG], [HOTEN], [GIOITINH], [SDT], [NGAYSINH], [EMAIL], [NGAYTAO], [NGAYSUA], [NGUOITAO], [NGUOISUA], [TRANGTHAI]) VALUES (1, N'KH001', N'Tran Tien Dung', 1, N'0375811233', CAST(N'2004-11-04' AS Date), N'dungt1689@gmail.com', CAST(N'2024-05-14' AS Date), CAST(N'2024-05-14' AS Date), N'quan ly ', N'quan ly', 1)
SET IDENTITY_INSERT [dbo].[KhachHang] OFF
GO
SET IDENTITY_INSERT [dbo].[MauSac] ON 

INSERT [dbo].[MauSac] ([IDMAUSAC], [MAMAU], [TENMAU], [TRANGTHAI]) VALUES (1, N'MS01', N'Xanh', 1)
INSERT [dbo].[MauSac] ([IDMAUSAC], [MAMAU], [TENMAU], [TRANGTHAI]) VALUES (2, N'MS02', N'Den', 1)
INSERT [dbo].[MauSac] ([IDMAUSAC], [MAMAU], [TENMAU], [TRANGTHAI]) VALUES (3, N'MS03', N'Tim', 1)
SET IDENTITY_INSERT [dbo].[MauSac] OFF
GO
SET IDENTITY_INSERT [dbo].[SanPham] ON 

INSERT [dbo].[SanPham] ([IDSANPHAM], [MASANPHAM], [TENSP], [TRANGTHAI]) VALUES (1, N'SP01', N'Polo', 1)
INSERT [dbo].[SanPham] ([IDSANPHAM], [MASANPHAM], [TENSP], [TRANGTHAI]) VALUES (2, N'SP02', N'Phong', 1)
INSERT [dbo].[SanPham] ([IDSANPHAM], [MASANPHAM], [TENSP], [TRANGTHAI]) VALUES (3, N'SP03', N'Da', 1)
SET IDENTITY_INSERT [dbo].[SanPham] OFF
GO
SET IDENTITY_INSERT [dbo].[SanPhamChiTiet] ON 

INSERT [dbo].[SanPhamChiTiet] ([IDSPCT], [IDSANPHAM], [IDCHATLIEU], [IDTHUONGHIEU], [IDMAUSAC], [IDSIZE], [SOLUONG], [GIA], [MOTA], [NGUOITAO], [NGUOISUA], [NGAYTAO], [NGAYSUA], [TRANGTHAI]) VALUES (1, 1, 1, 1, 1, 1, 10, 50.0000, N'khong', N'quan ly', N'quan ly', CAST(N'2024-05-14' AS Date), CAST(N'2024-05-14' AS Date), 1)
INSERT [dbo].[SanPhamChiTiet] ([IDSPCT], [IDSANPHAM], [IDCHATLIEU], [IDTHUONGHIEU], [IDMAUSAC], [IDSIZE], [SOLUONG], [GIA], [MOTA], [NGUOITAO], [NGUOISUA], [NGAYTAO], [NGAYSUA], [TRANGTHAI]) VALUES (2, 1, 1, 1, 1, 2, 10, 40.0000, N'khong', N'quan ly ', N'quan ly', CAST(N'2024-05-14' AS Date), CAST(N'2024-05-14' AS Date), 1)
SET IDENTITY_INSERT [dbo].[SanPhamChiTiet] OFF
GO
SET IDENTITY_INSERT [dbo].[Size] ON 

INSERT [dbo].[Size] ([IDSIZE], [MASIZE], [TENSIZE], [TRANGTHAI]) VALUES (1, N'SZ01', N'S', 1)
INSERT [dbo].[Size] ([IDSIZE], [MASIZE], [TENSIZE], [TRANGTHAI]) VALUES (2, N'SZ02', N'XS', 1)
INSERT [dbo].[Size] ([IDSIZE], [MASIZE], [TENSIZE], [TRANGTHAI]) VALUES (3, N'SZ03', N'M', 1)
SET IDENTITY_INSERT [dbo].[Size] OFF
GO
SET IDENTITY_INSERT [dbo].[ThuongHieu] ON 

INSERT [dbo].[ThuongHieu] ([IDTHUONGHIEU], [MATHUONGHIEU], [TENTHUONGHIEU], [TRANGTHAI]) VALUES (1, N'TH01', N'adidas', 1)
INSERT [dbo].[ThuongHieu] ([IDTHUONGHIEU], [MATHUONGHIEU], [TENTHUONGHIEU], [TRANGTHAI]) VALUES (2, N'TH02', N'nike', 1)
INSERT [dbo].[ThuongHieu] ([IDTHUONGHIEU], [MATHUONGHIEU], [TENTHUONGHIEU], [TRANGTHAI]) VALUES (3, N'TH02', N'jordan', 1)
SET IDENTITY_INSERT [dbo].[ThuongHieu] OFF
GO
SET IDENTITY_INSERT [dbo].[Voucher] ON 

INSERT [dbo].[Voucher] ([IDVOUCHER], [MAVOUCHER], [TENVOUCHER], [DONTOITHIEU], [MUCGIAMGIA], [LOAIVC], [SOLUONG], [NGAYBATDAU], [NGAYKETTHUC], [NGAYTAO], [NGAYSUA], [NGUOITAO], [NGUOISUA], [TRANGTHAI]) VALUES (1, N'MVC01', N'deal 30k', 200.0000, 30000, N'tien', 10, CAST(N'2024-01-01' AS Date), CAST(N'2024-05-30' AS Date), CAST(N'2024-05-14' AS Date), CAST(N'2024-05-14' AS Date), N'quan ly', N'quan ly', 1)
INSERT [dbo].[Voucher] ([IDVOUCHER], [MAVOUCHER], [TENVOUCHER], [DONTOITHIEU], [MUCGIAMGIA], [LOAIVC], [SOLUONG], [NGAYBATDAU], [NGAYKETTHUC], [NGAYTAO], [NGAYSUA], [NGUOITAO], [NGUOISUA], [TRANGTHAI]) VALUES (2, N'MVC02', N'deal 50k', 300.0000, 50000, N'tien', 10, CAST(N'2024-05-14' AS Date), CAST(N'2024-06-14' AS Date), CAST(N'2024-05-14' AS Date), CAST(N'2024-05-14' AS Date), N'quan ly', N'quan ly', 1)
SET IDENTITY_INSERT [dbo].[Voucher] OFF
GO
ALTER TABLE [dbo].[HoaDon] ADD  CONSTRAINT [DF_HoaDon_NGAYTHANHTOAN]  DEFAULT (getdate()) FOR [NGAYTHANHTOAN]
GO
ALTER TABLE [dbo].[HoaDon] ADD  CONSTRAINT [DF_HoaDon_NGAYTAO]  DEFAULT (getdate()) FOR [NGAYTAO]
GO
ALTER TABLE [dbo].[HoaDonChiTiet] ADD  CONSTRAINT [DF_HoaDonChiTiet_NGAYTAO]  DEFAULT (getdate()) FOR [NGAYTAO]
GO
ALTER TABLE [dbo].[HoaDonChiTiet] ADD  CONSTRAINT [DF_HoaDonChiTiet_NGAYSUA]  DEFAULT (getdate()) FOR [NGAYSUA]
GO
ALTER TABLE [dbo].[KhachHang] ADD  CONSTRAINT [DF_KhachHang_NGAYSINH]  DEFAULT (getdate()) FOR [NGAYSINH]
GO
ALTER TABLE [dbo].[KhachHang] ADD  CONSTRAINT [DF_KhachHang_NGAYTAO]  DEFAULT (getdate()) FOR [NGAYTAO]
GO
ALTER TABLE [dbo].[KhachHang] ADD  CONSTRAINT [DF_KhachHang_NGAYSUA]  DEFAULT (getdate()) FOR [NGAYSUA]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [DF_NhanVien_NGAYSINH]  DEFAULT (getdate()) FOR [NGAYSINH]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [DF_NhanVien_NGAYTAO]  DEFAULT (getdate()) FOR [NGAYTAO]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [DF_NhanVien_NGAYSUA]  DEFAULT (getdate()) FOR [NGAYSUA]
GO
ALTER TABLE [dbo].[SanPhamChiTiet] ADD  CONSTRAINT [DF_SanPhamChiTiet\_NGAYTAO]  DEFAULT (getdate()) FOR [NGAYTAO]
GO
ALTER TABLE [dbo].[SanPhamChiTiet] ADD  CONSTRAINT [DF_SanPhamChiTiet\_NGAYSUA]  DEFAULT (getdate()) FOR [NGAYSUA]
GO
ALTER TABLE [dbo].[Voucher] ADD  CONSTRAINT [DF_Voucher_NGAYBATDAU]  DEFAULT (getdate()) FOR [NGAYBATDAU]
GO
ALTER TABLE [dbo].[Voucher] ADD  CONSTRAINT [DF_Voucher_NGAYKETTHUC]  DEFAULT (getdate()) FOR [NGAYKETTHUC]
GO
ALTER TABLE [dbo].[Voucher] ADD  CONSTRAINT [DF_Voucher_NGAYTAO]  DEFAULT (getdate()) FOR [NGAYTAO]
GO
ALTER TABLE [dbo].[Voucher] ADD  CONSTRAINT [DF_Voucher_NGAYSUA]  DEFAULT (getdate()) FOR [NGAYSUA]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_HoaDonChiTiet] FOREIGN KEY([IDHDCT])
REFERENCES [dbo].[HoaDonChiTiet] ([IDHDCT])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_HoaDonChiTiet]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_KhachHang] FOREIGN KEY([IDKHACHHANG])
REFERENCES [dbo].[KhachHang] ([IDKHACHHANG])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_KhachHang]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_NhanVien] FOREIGN KEY([IDNHANVIEN])
REFERENCES [dbo].[NhanVien] ([IDNHANVIEN])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_NhanVien]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_Voucher] FOREIGN KEY([IDVOUCHER])
REFERENCES [dbo].[Voucher] ([IDVOUCHER])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_Voucher]
GO
ALTER TABLE [dbo].[HoaDonChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_HoaDonChiTiet_SanPhamChiTiet] FOREIGN KEY([IDSPCT])
REFERENCES [dbo].[SanPhamChiTiet] ([IDSPCT])
GO
ALTER TABLE [dbo].[HoaDonChiTiet] CHECK CONSTRAINT [FK_HoaDonChiTiet_SanPhamChiTiet]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_ChucVu] FOREIGN KEY([IDCHUCVU])
REFERENCES [dbo].[ChucVu] ([IDCHUCVU])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_ChucVu]
GO
ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_SanPhamChiTiet_ChatLieu] FOREIGN KEY([IDCHATLIEU])
REFERENCES [dbo].[ChatLieu] ([IDCHATLIEU])
GO
ALTER TABLE [dbo].[SanPhamChiTiet] CHECK CONSTRAINT [FK_SanPhamChiTiet_ChatLieu]
GO
ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_SanPhamChiTiet_MauSac] FOREIGN KEY([IDMAUSAC])
REFERENCES [dbo].[MauSac] ([IDMAUSAC])
GO
ALTER TABLE [dbo].[SanPhamChiTiet] CHECK CONSTRAINT [FK_SanPhamChiTiet_MauSac]
GO
ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_SanPhamChiTiet_SanPham] FOREIGN KEY([IDSANPHAM])
REFERENCES [dbo].[SanPham] ([IDSANPHAM])
GO
ALTER TABLE [dbo].[SanPhamChiTiet] CHECK CONSTRAINT [FK_SanPhamChiTiet_SanPham]
GO
ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_SanPhamChiTiet_Size] FOREIGN KEY([IDSIZE])
REFERENCES [dbo].[Size] ([IDSIZE])
GO
ALTER TABLE [dbo].[SanPhamChiTiet] CHECK CONSTRAINT [FK_SanPhamChiTiet_Size]
GO
ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_SanPhamChiTiet_Thuonghieu] FOREIGN KEY([IDTHUONGHIEU])
REFERENCES [dbo].[Thuonghieu] ([IDTHUONGHIEU])
GO
ALTER TABLE [dbo].[SanPhamChiTiet] CHECK CONSTRAINT [FK_SanPhamChiTiet_thuonghieu]
GO
select sct.IDSPCT,s.TENSP,m.TENMAU,c.TENCHATLIEU,z.TENSIZE,t.TENTHUONGHIEU,SOLUONG,GIA from SanPhamChiTiet sct 
join SanPham s on s.IDSANPHAM= sct.IDSANPHAM
join MauSac m on m.IDMAUSAC = sct.IDMAUSAC
join ChatLieu c on c.IDCHATLIEU = sct.IDCHATLIEU
join Size z on z.IDSIZE = sct.IDSIZE
join ThuongHieu t on t.IDTHUONGHIEU = sct.IDTHUONGHIEU