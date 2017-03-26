package demo.data;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

 @CreatedDate
 private Long createdAt;

 @LastModifiedDate
 private Long lastModified;

 public BaseEntity() {
 }

 public Long getCreatedAt() {
  return createdAt;
 }

 public void setCreatedAt(Long createdAt) {
  this.createdAt = createdAt;
 }

 public Long getLastModified() {
  return lastModified;
 }

 public void setLastModified(Long lastModified) {
  this.lastModified = lastModified;
 }

 @Override
 public String toString() {
  return "BaseEntity{" + "createdAt=" + createdAt + ", lastModified="
   + lastModified + '}';
 }

 @Override
 public boolean equals(Object o) {
  if (this == o)
   return true;
  if (o == null || getClass() != o.getClass())
   return false;

  BaseEntity that = (BaseEntity) o;

  if (createdAt != null ? !createdAt.equals(that.createdAt)
   : that.createdAt != null)
   return false;
  return lastModified != null ? lastModified.equals(that.lastModified)
   : that.lastModified == null;

 }

 @Override
 public int hashCode() {
  int result = createdAt != null ? createdAt.hashCode() : 0;
  result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
  return result;
 }
}
