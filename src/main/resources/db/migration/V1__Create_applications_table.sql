-- Create applications table
CREATE TABLE applications (
    id UUID PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    role_title VARCHAR(255) NOT NULL,
    application_type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    application_deadline DATE,
    applied_date DATE,
    location VARCHAR(255),
    job_posting_url VARCHAR(2048),
    notes TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for better query performance
CREATE INDEX idx_applications_user_id ON applications(user_id);
CREATE INDEX idx_applications_status ON applications(status);
CREATE INDEX idx_applications_deadline ON applications(application_deadline);
CREATE INDEX idx_applications_company_name ON applications(company_name);

-- Add comments for documentation
COMMENT ON TABLE applications IS 'Stores internship and job applications';
COMMENT ON COLUMN applications.id IS 'Primary key (UUID)';
COMMENT ON COLUMN applications.user_id IS 'ID of the user who owns this application';
COMMENT ON COLUMN applications.company_name IS 'Name of the company';
COMMENT ON COLUMN applications.role_title IS 'Title of the role/position';
COMMENT ON COLUMN applications.application_type IS 'Type of application: INTERNSHIP, FULL_TIME, OTHER';
COMMENT ON COLUMN applications.status IS 'Current status: PLANNING, APPLIED, INTERVIEW, OFFER, REJECTED, WITHDRAWN';
COMMENT ON COLUMN applications.application_deadline IS 'Deadline for submitting the application';
COMMENT ON COLUMN applications.applied_date IS 'Date when the application was submitted';
COMMENT ON COLUMN applications.location IS 'Location of the position';
COMMENT ON COLUMN applications.job_posting_url IS 'URL to the job posting';
COMMENT ON COLUMN applications.notes IS 'Additional notes about the application';
COMMENT ON COLUMN applications.created_at IS 'Timestamp when the record was created';
COMMENT ON COLUMN applications.updated_at IS 'Timestamp when the record was last updated';

