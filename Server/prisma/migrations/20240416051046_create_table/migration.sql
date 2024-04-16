-- CreateTable
CREATE TABLE "Product" (
    "productId" SERIAL NOT NULL,
    "productName" TEXT NOT NULL,
    "productPrice" TEXT NOT NULL,
    "productDescription" TEXT,
    "productCategory" TEXT,
    "availableColorList" TEXT[],
    "availableSizeList" TEXT[],
    "productRemainingStock" INTEGER NOT NULL,
    "productImageURLList" TEXT[],
    "productPostedDate" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT "Product_pkey" PRIMARY KEY ("productId")
);

-- CreateTable
CREATE TABLE "Command" (
    "commandId" SERIAL NOT NULL,
    "productId" INTEGER NOT NULL,
    "studentId" INTEGER NOT NULL,
    "productSizeChosen" TEXT NOT NULL,
    "productColorChosen" TEXT NOT NULL,
    "commandDate" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT "Command_pkey" PRIMARY KEY ("commandId")
);

-- CreateTable
CREATE TABLE "Student" (
    "studentId" SERIAL NOT NULL,
    "studentCardNum" TEXT NOT NULL,
    "studentName" TEXT NOT NULL,
    "studentFirstname" TEXT NOT NULL,
    "studentPassword" TEXT NOT NULL,

    CONSTRAINT "Student_pkey" PRIMARY KEY ("studentId")
);

-- CreateTable
CREATE TABLE "Admin" (
    "adminId" SERIAL NOT NULL,
    "studentCardNum" TEXT NOT NULL,
    "adminPassword" TEXT NOT NULL,
    "adminKey" TEXT NOT NULL,

    CONSTRAINT "Admin_pkey" PRIMARY KEY ("adminId")
);

-- CreateIndex
CREATE UNIQUE INDEX "Student_studentCardNum_key" ON "Student"("studentCardNum");

-- CreateIndex
CREATE UNIQUE INDEX "Admin_studentCardNum_key" ON "Admin"("studentCardNum");

-- CreateIndex
CREATE UNIQUE INDEX "Admin_adminKey_key" ON "Admin"("adminKey");

-- AddForeignKey
ALTER TABLE "Command" ADD CONSTRAINT "Command_productId_fkey" FOREIGN KEY ("productId") REFERENCES "Product"("productId") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Command" ADD CONSTRAINT "Command_studentId_fkey" FOREIGN KEY ("studentId") REFERENCES "Student"("studentId") ON DELETE RESTRICT ON UPDATE CASCADE;
