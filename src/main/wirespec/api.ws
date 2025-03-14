type OrderDTO{
  orderId: String,
  totalAmount: Number,
  amountPaid: Number
}

endpoint OrdersDTO GET /orders -> {
    200 -> OrderDTO[]
}

endpoint OrderDTO GET /orders/{orderId : String} -> {
    200 -> OrderDTO
}
