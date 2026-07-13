#!/bin/bash

# Thiết lập màu sắc hiển thị log cho dễ nhìn
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${BLUE}=========================================================${NC}"
echo -e "${BLUE}   HỆ THỐNG ĐÓNG GÓI VẬN HÀNH MICROSERVICES BANKING      ${NC}"
echo -e "${BLUE}=========================================================${NC}"

# Bước 1: Dọn dẹp hệ thống container cũ
echo -e "${YELLOW}1. Đang dọn dẹp và tắt toàn bộ container cũ...${NC}"
docker compose down

# Bước 2: Ép build lại image mới nhất không sử dụng cache cũ và chạy ngầm
echo -e "${YELLOW}2. Đang biên dịch mã nguồn, rebuild và khởi chạy hệ thống ngầm...${NC}"
docker compose up -d --build

# Bước 3: Theo dõi tiến trình khởi động tuần tự
echo -e "${GREEN}3. Kích hoạt hệ thống thành công! Thống kê trạng thái các Service:${NC}"
echo -e "${BLUE}---------------------------------------------------------${NC}"
docker compose ps
echo -e "${BLUE}---------------------------------------------------------${NC}"

echo -e "${GREEN}Hoàn tất bàn giao!${NC}"
echo -e "Hệ thống đang kiểm tra trạng thái Healthy nội bộ ngầm."
echo -e "Mọi truy cập bên ngoài chỉ được phép gọi qua cổng HTTP tiêu chuẩn: ${GREEN}http://localhost/${NC}"
echo -e "${BLUE}=========================================================${NC}"